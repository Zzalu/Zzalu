package com.samsamoo.zzalu.member.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;
    @Column(nullable = false, length = 100, unique = true)
    private String username; //아이디
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String userEmail;
    @Column(nullable = false, length = 100)
    private String nickname;
    @Builder.Default
    private String profilePath = null;
    @Builder.Default
    private LocalDateTime enrollDate = LocalDateTime.now();

//    @Column(columnDefinition="bit(1) default 0") // False
    @Builder.Default
    private boolean accountNonLocked = true; // 계정 공개 여부

    @Builder.Default
    private boolean enabled = true; // 계정 활성화 여부

    @Builder.Default
    private boolean accountNonExpired = true; // 계정 탈퇴 여부?

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<String>() {{
        add("USER");
    }};

    //팔로잉
    @Builder.Default
    @ManyToMany
    private Set<Member> following = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "following")
    private Set<Member> follower = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void followMember(Member member){
        this.getFollowing().add(member);
        member.getFollower().add(this);
//        log.info("youfollower = {}", member.getFollower());
//        log.info("me = {}", this);

    }

    public void unfollowMember(Member yourMember) {
        for (Member member : following) {
            if (member == yourMember) {
                this.getFollowing().remove(member);
                member.getFollower().remove(this);
            }
        }
    }
}