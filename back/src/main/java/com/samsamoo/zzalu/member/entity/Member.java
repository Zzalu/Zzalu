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
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String username; //아이디
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String userEmail;
    @Column(nullable = false, length = 100)
    private String nickname;
    @Builder.Default
    private String profileMessage = null;
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
    private List<Member> following = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "following")
    private List<Member> follower = new ArrayList<>();


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
        List<Member> myFollowings = this.getFollowing();
        if (!myFollowings.contains(member)) {
            this.getFollowing().add(0, member);
            member.getFollower().add(0, this);
        }
    }

    public void unfollowMember(Member you) {
        for(Iterator<Member> myItr = following.iterator(); myItr.hasNext();) {
            Member member = myItr.next();
            if (member.equals(you)) {
                myItr.remove();
            }
        }
        for(Iterator<Member> yourItr = you.getFollower().iterator(); yourItr.hasNext();) {
            Member member = yourItr.next();
            if (member.equals(this)) {
                yourItr.remove();
            }
        }
    }
}