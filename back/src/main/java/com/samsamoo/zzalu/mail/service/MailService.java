package com.samsamoo.zzalu.mail.service;

import com.samsamoo.zzalu.mail.utils.MailUtils;
import com.samsamoo.zzalu.mail.dto.AuthResponse;
import com.samsamoo.zzalu.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Autowired
    private JavaMailSender mailSender;
    private final MemberRepository memberRepository;
    private int size;

    public boolean checkUniqueUserEmail(String email) {
        boolean unique = !memberRepository.existsMemberByUserEmail(email);
        return unique;
    }
    //인증키 생성
    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    //인증코드 난수 발생
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }
        return buffer.toString();
    }

    public AuthResponse sendMail(String userEmail) {
        // 이메일 있는지 검사
        if (!checkUniqueUserEmail(userEmail)) {
            throw new IllegalArgumentException("이미 존재하는 email 주소입니다.");
        };

        // 4자리 난수 인증번호 생성
        String authKey = getKey(4);

        // 인증 메일 보내기
        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("[Zzalu]회원가입 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>[이메일]</h1>")
                    .append("<p>아래 인증코드를 앱에 입력해주세요.</p>")
                    .append("<p>인증코드: "+ authKey +"</p>")
                    .toString());
            sendMail.setFrom(username, "관리자");
            sendMail.setTo(userEmail);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new AuthResponse(authKey);

    }

}
