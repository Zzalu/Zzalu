package com.samsamoo.zzalu.board.dto;

import com.samsamoo.zzalu.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MembersBoardInfo {
    private Long id;
    private String boardName;
    private String thumbnailPath;
}
