package com.samsamoo.zzalu.board.dto;

import com.samsamoo.zzalu.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MembersBoardList {
    private List<MembersBoardInfo> boards;
}
