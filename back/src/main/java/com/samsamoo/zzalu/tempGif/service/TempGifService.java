package com.samsamoo.zzalu.tempGif.service;

import com.samsamoo.zzalu.advice.NotFoundException;
import com.samsamoo.zzalu.amazonS3.upLoader.S3Uploader;
import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.repository.GifsRepository;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.member.exception.AuthorizationException;
import com.samsamoo.zzalu.member.exception.NotMatchException;
import com.samsamoo.zzalu.member.repo.MemberRepository;
import com.samsamoo.zzalu.member.service.MemberService;
import com.samsamoo.zzalu.tempGif.dto.TempGifRequest;
import com.samsamoo.zzalu.tempGif.dto.TempGifDTO;
import com.samsamoo.zzalu.tempGif.entity.TempGif;
import com.samsamoo.zzalu.tempGif.enums.RequestType;
import com.samsamoo.zzalu.tempGif.repo.TempGifRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TempGifService {
    private final JwtTokenProvider jwtTokenProvider;
    private final TempGifRepository tempGifRepository;
    private final GifsRepository gifsRepository;
    private final MemberRepository memberRepository;

    private final S3Uploader s3Uploader;
    public Long createGifReq(String token, TempGifRequest request) throws IOException {
        Member writer = jwtTokenProvider.getMember(token);
        checkManager(writer);

        if (request.getTempGifMultipartFile() != null) {
            String tempUrl = s3Uploader.upload(request.getTempGifMultipartFile(), "TempGif");
            request.setReturnUrl(tempUrl);
        }
        TempGif tempGif = tempGifRepository.save(request.toEntity(writer));
        return tempGif.getId();
    }
    public void checkManager(Member member) {
        if (member.getIsManager() == false) {
            throw new AuthorizationException();
        }
    }
    public Gifs returnGifs(Long gifsId) {
        return gifsRepository.findById(gifsId)
                .orElseThrow(()-> new NotFoundException("해당 원본 gif를 찾을 수 없습니다."));
    }

    public void increaseCount(String token, Long tempId) {
        Member member = jwtTokenProvider.getMember(token);
        checkManager(member);
        TempGif tempGif = tempGifRepository.findById(tempId)
                .orElseThrow(() -> new NotFoundException("해당 임시 게시물을 찾을 수 없습니다."));
        int permittedCount = tempGif.getPermittedCount()+1;
        if (permittedCount >= 3) {
            // tempGif > real Gifs
            // Create 요청이었을 때
            if (tempGif.getRequestType().equals(RequestType.CREATE)) {
                Gifs gifs = Gifs.builder()
                        .gifPath(tempGif.getGifPath())
                        .tags(tempGif.getTags())
                        .relationsVideo(tempGif.getRelationsVideo())
                        .description(tempGif.getDescription())
                        .lastUpdateUsername(tempGif.getWriterUsername())
                        .build();
                gifsRepository.save(gifs);
            } else {
                // Update 요청이었을 때
                Gifs originGifs = returnGifs(tempGif.getOriginGifsId());
                originGifs.update(tempGif);
                gifsRepository.save(originGifs);
            }
            member.increaseCount();
            memberRepository.save(member);
            // tempGif 삭제
            tempGifRepository.delete(tempGif);
        } else {
            // 그대로 저장
            tempGif.increseCount(permittedCount);
            tempGifRepository.save(tempGif);
        }
    }

    public List<TempGif> getAllTempGif(String token) {
        checkManager(jwtTokenProvider.getMember(token));
        List<TempGif> list = tempGifRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    public void deleteTempGif(String token, Long tempId) {
        checkManager(jwtTokenProvider.getMember(token));
        TempGif tempGif = tempGifRepository.findById(tempId)
                .orElseThrow(()-> new NotFoundException("해당 임시 게시물을 찾을 수 없습니다."));
        tempGifRepository.delete(tempGif);
    }
}