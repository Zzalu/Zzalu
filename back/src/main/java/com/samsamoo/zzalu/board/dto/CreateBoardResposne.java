package com.samsamoo.zzalu.board.dto;

import com.samsamoo.zzalu.board.entity.Board;
import lombok.Getter;

@Getter
public class CreateBoardResposne {
    private Long id;
    private String boardName;
    public CreateBoardResposne(Board board) {
        this.id = board.getId();
        this.boardName = board.getBoardName();
    }
}
