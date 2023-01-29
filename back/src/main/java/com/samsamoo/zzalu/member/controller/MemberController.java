package com.samsamoo.zzalu.member.controller;

import com.samsamoo.zzalu.auth.dto.LoginRequest;
import com.samsamoo.zzalu.auth.dto.TokenInfo;
import com.samsamoo.zzalu.mail.dto.EmailResponse;
import com.samsamoo.zzalu.mail.dto.EmailRequest;
import com.samsamoo.zzalu.mail.service.MailService;
import com.samsamoo.zzalu.member.dto.MemberDTO;
import com.samsamoo.zzalu.member.dto.SignupRequest;
import com.samsamoo.zzalu.member.dto.UniqueResponse;
import com.samsamoo.zzalu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    // 1. 아이디 중복확인 (GET)
    // 2. 닉네임 중복확인 (GET)
    // 3. 비밀번호와 비밀번호 확인 일치하는지 (GET)
    // 3. 이메일 확인 및 코드 전송 (4자리 숫자) > (POST)
    // 4. 회원가입 요청 (마무리) > (POST)
    private final MemberService memberService;
    private final MailService mailService;

    @PostMapping("/signup")
    public ResponseEntity<MemberDTO> signup(@Valid @RequestBody SignupRequest signupRequest) {
        MemberDTO memberDTO = memberService.signup(signupRequest);
        return ResponseEntity.created(URI.create("/members/" + memberDTO.getId())).body(memberDTO);
    }

    @PostMapping("/signup/email")
    public ResponseEntity<EmailResponse> validateUniqueNickname(@RequestBody @Valid EmailRequest emailRequest) {
        EmailResponse emailResponse = mailService.sendMail(emailRequest.getUserEmail());
        return ResponseEntity.ok().body(emailResponse);
    }

    @GetMapping(value = "/signup/exists", params = "username")
    public ResponseEntity<UniqueResponse> validateUniqueUsername(@RequestParam String username) {
        UniqueResponse uniqueResponse = memberService.checkUniqueUsername(username);
        return ResponseEntity.ok().body(uniqueResponse);
    }

    @GetMapping(value = "/signup/exists", params = "nickname")
    public ResponseEntity<UniqueResponse> validateUniqueNickname(@RequestParam String nickname) {
        UniqueResponse uniqueResponse = memberService.checkUniqueNickname(nickname);
        return ResponseEntity.ok().body(uniqueResponse);
    }

    @PostMapping("/login")
    public  ResponseEntity<TokenInfo> login(@RequestBody LoginRequest loginRequest) {
        TokenInfo tokenInfo = memberService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok().body(tokenInfo);
    }

    @GetMapping(value = "/my-info", params = "username")
    public  ResponseEntity<MemberDTO> getMyProfile(@RequestHeader(value = "Authorization") String token, @RequestParam String username) {
        MemberDTO memberDTO = memberService.getMyProfile(token, username);
        return ResponseEntity.ok().body(memberDTO);
    }

}
