package com.samsamoo.zzalu.statistics.controller;

import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.repository.GifsRepository;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.statistics.entity.MemberTagStatistics;
import com.samsamoo.zzalu.statistics.repository.MemberTagStatisticsRepository;
import com.samsamoo.zzalu.statistics.service.MemberTagStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberTagStatisticsController {

    private final GifsRepository gifsRepository;
    private final MemberTagStatisticsService memberTagStatisticsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberTagStatisticsRepository memberTagStatisticsRepository;

    @GetMapping("/use")
    @ResponseBody
    public boolean updateTagUseCount(@RequestHeader(value = "Authorization")String bearerToken, @RequestParam("gifId") Long gifId){

        String token = bearerToken.substring(7);
        Member requestMember = jwtTokenProvider.getMember(token);

        if(gifsRepository.findById(gifId).isPresent()){
            Gifs gif = gifsRepository.findById(gifId).get();
            String[] tags = gif.getTags().split(",");

            for(int index = 0; index < tags.length; ++index) {
                Optional<MemberTagStatistics> optionalMemberTagStatistics = memberTagStatisticsService.findByTagAndMemberId(tags[index], requestMember.getId());

                if(optionalMemberTagStatistics.isPresent()) {
                    MemberTagStatistics memberTagStatistics = optionalMemberTagStatistics.get();
                    memberTagStatistics.setCount(memberTagStatistics.getCount() + 1);
                    memberTagStatisticsRepository.save(memberTagStatistics);
                } else {
                    MemberTagStatistics newMemberTagStatistics = new MemberTagStatistics();
                    newMemberTagStatistics.setMemberId(requestMember.getId());
                    newMemberTagStatistics.setTag(tags[index]);
                    newMemberTagStatistics.setCount(1L);
                    memberTagStatisticsRepository.save(newMemberTagStatistics);
                }
            }
            return true;
        } else {
            System.out.println("gif Error");
            return false;
        }
    }


    // AccessToken이 아니라 MemberId로 받도록 수정
    @GetMapping("/member")
    @ResponseBody
    public List<MemberTagStatistics> findAllByMemberId(@RequestParam("memberId") Long memberId){
        return memberTagStatisticsService.findAllByMemberId(memberId);
    }

}
