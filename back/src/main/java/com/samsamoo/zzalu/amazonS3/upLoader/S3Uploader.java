package com.samsamoo.zzalu.amazonS3.upLoader;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component

/**
 @Component는 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 Annotation 입니다.

 @Component 어노테이션이 적용된 클래스를 식별하고, 그러한 클래스의 빈을 생성하여 ApplicationContext에 등록합니다.
 */
public class S3Uploader {

    /**
     * 여기서 재밌는 것은 별다른 Configuration 코드 없이 AmazonS3Client 를 DI 받은것인데요.
     * Spring Boot Cloud AWS를 사용하게 되면 S3 관련 Bean을 자동 생성해줍니다.
     * 그래서 아래 3개는 직접 설정할 필요가 없습니다.
     */
    private final AmazonS3Client amazonS3Client; //알아서 빈으로 등록된다는 얘기가 있음
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


   //S3에 전달할 수 있도록 MultiPartFile 을 File로 전환한다.
    //S3에서 MultiPartFile 타입은 전송이 안된다.
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        //S3에 업로드 하는 함수를 부른다.
        return upload(uploadFile, dirName);
    }


    private String upload(File uploadFile, String dirName) {
        System.out.println(dirName);
        //파일이름 중복 방지 하기 위해 UUID값을 붙여줌
        String fileName = dirName + "/" + UUID.randomUUID() + "-"+ uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        System.out.println("확인");
        System.out.println(uploadImageUrl);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        //전환된 File을 S3 에 Public 읽기 권한으로 put 한다.  외부에서 정적 파일을 읽을 수 있도록 하기 위함이다.
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        //업로드된 주소를 반환한다.
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }



    //MultipartFile - > file 로 변환
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }


    private void removeNewFile(File targetFile) {
        //Multipartfile ->File 로 전환되면서 로컬에 파일 생성된 것을 삭제한다.
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }
}
