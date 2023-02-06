package com.samsamoo.zzalu.member.controller;

import com.samsamoo.zzalu.auth.dto.LoginRequest;
import com.samsamoo.zzalu.auth.dto.TokenInfo;
import com.samsamoo.zzalu.member.dto.*;
import com.samsamoo.zzalu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //--------------------------------------회원가입-------------------------------------------
    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequest signupRequest) {
        memberService.signup(signupRequest);
        return ResponseEntity.created(URI.create("/members/" + signupRequest.getUsername())).build();
    }

    //--------------------------------------아이디 중복 체크-------------------------------------------
    @GetMapping(value = "/exists", params = "username")
    public ResponseEntity<UniqueResponse> validateUniqueUsername(@RequestParam String username) {
        // username 유효성 검사.
        Boolean unique = memberService.checkUniqueUsername(username);
        return ResponseEntity.ok().body(new UniqueResponse(unique));
    }

    //--------------------------------------닉네임 중복 체크-------------------------------------------
    @GetMapping(value = "/exists", params = "nickname")
    public ResponseEntity<UniqueResponse> validateUniqueNickname(@RequestParam String nickname) {
        Boolean unique = memberService.checkUniqueNickname(nickname);
        return ResponseEntity.ok().body(new UniqueResponse(unique));
    }

    //--------------------------------------로그인-------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody LoginRequest loginRequest) {
        TokenInfo tokenInfo = memberService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok().body(tokenInfo);
    }

    //--------------------------------------나의 프로필 확인-------------------------------------------
    @GetMapping(value = "/my-profile")
    public ResponseEntity getMyProfile(@RequestHeader(value = "Authorization") String bearerToken) {
        return ResponseEntity.ok().body("OK");
    }

    //--------------------------------------프로필 확인-------------------------------------------
    @GetMapping("/{username}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable String username) {
        ProfileDTO profile = memberService.getProfile(username);
        return ResponseEntity.ok().body(profile);
    }

    //--------------------------------------회원 정보 수정-------------------------------------------
    @PatchMapping
    public ResponseEntity<UpdateMember> updateMember(@RequestHeader(value = "Authorization") String bearerToken, @ModelAttribute UpdateMemberRequest request) throws IOException {
        String token = bearerToken.substring(7);
        UpdateMember response = memberService.updateMember(token, request);
        return ResponseEntity.ok().body(response);
    }
    //--------------------------------------회원 탈퇴-------------------------------------------
    @DeleteMapping
    public ResponseEntity deleteMember(@RequestHeader(value = "Authorization") String bearerToken, @RequestBody PasswordDTO passwordDTO) {
        String token = bearerToken.substring(7);
        memberService.deleteMember(token, passwordDTO.getRawPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //--------------------------------------비밀번호 변경-------------------------------------------
    @PatchMapping("/pass")
    public ResponseEntity changePass(@Valid @RequestBody ChangePassRequest request) {
        memberService.changePass(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}