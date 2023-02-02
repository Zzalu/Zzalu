package com.samsamoo.zzalu.board.service;

import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.board.dto.CreateBoardResposne;
import com.samsamoo.zzalu.board.entity.Board;
import com.samsamoo.zzalu.board.repo.BoardRepository;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.member.exception.NotMatchException;
import com.samsamoo.zzalu.member.repo.MemberRepository;
import com.samsamoo.zzalu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public CreateBoardResposne createBoard(String token, String requestName) {
        // 토큰 검증
        memberService.checkToken(token);

        // 토큰에서 맴버 꺼내서 아이디까지 꺼내기
        Member requestMember = jwtTokenProvider.getMember(token);
        Long memberId = requestMember.getId();

        // 보드 이름 중복인지 검증
        List<Board> myBoards = requestMember.getBoards();
        ArrayList<String> boardNames = new ArrayList<>();
        for (Board board : myBoards) {
            boardNames.add(board.getBoardName());
        }
        if (boardNames.contains(requestName)) {
            throw new NotMatchException("보드 이름이 중복입니다.");
        }

        // 보드를 생성
        Board board = Board.builder()
                .boardName(requestName)
                .member(requestMember)
                .build();

        // 맴버에도 보드 저장?
        requestMember.createBoard(board);

        // 보드를 저장
        boardRepository.save(board);
        memberRepository.save(requestMember);

        return new CreateBoardResposne(board);
    }
}
