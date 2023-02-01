package com.samsamoo.zzalu.mail.controller;

import com.samsamoo.zzalu.mail.dto.ChangePassEmailRequest;
import com.samsamoo.zzalu.mail.dto.SignupEmailRequest;
import com.samsamoo.zzalu.mail.dto.EmailResponse;
import com.samsamoo.zzalu.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    //--------------------------------------회원가입 이메일 인증-------------------------------------------
    @PostMapping("/signup")
    public ResponseEntity<EmailResponse> sendSignupEmail(@RequestBody @Valid SignupEmailRequest signupEmailRequest) {
        EmailResponse emailResponse = mailService.sendMail(signupEmailRequest.getUserEmail());
        return ResponseEntity.ok().body(emailResponse);
    }

    //--------------------------------------비번 변경 이메일 인증-------------------------------------------
    @PostMapping("/pass")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody @Valid ChangePassEmailRequest request) {

        EmailResponse emailResponse = mailService.sendChangeMail(request.getUserEmail(), request.getUsername());
        return ResponseEntity.ok().body(emailResponse);
    }
}
