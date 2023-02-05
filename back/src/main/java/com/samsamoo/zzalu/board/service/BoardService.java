package com.samsamoo.zzalu.board.service;

import com.samsamoo.zzalu.advice.BadRequestException;
import com.samsamoo.zzalu.advice.NotFoundException;
import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.board.dto.*;
import com.samsamoo.zzalu.board.entity.Board;
import com.samsamoo.zzalu.board.repo.BoardRepository;
import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.repository.GifsRepository;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.member.exception.MemberNotFoundException;
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
    private final GifsRepository gifsRepository;


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

    public GifsBoardList getGifsBoard(String token, Long gifId) {
        // 토큰 검증
        memberService.checkToken(token);
        // user 반환
        Member requestMember = jwtTokenProvider.getMember(token);

        // user의 보드 불러오기
        List<Board> boards = requestMember.getBoards();

        // new list 만들기
        List<GifsBoardInfo> gifsBoardInfos = new ArrayList<>();

        Gifs gif = gifsRepository.findById(gifId)
                .orElseThrow(() -> new BadRequestException("짤이 존재하지 않습니다."));

        // for문 돌면서 gif 포함 여부와 id를 dto에 넣기
        for(Board board : boards) {
            Boolean gifContainState = board.getGifs().contains(gif);
            GifsBoardInfo gifsBoardInfo = new GifsBoardInfo(board.getId(), board.getBoardName(), gifContainState);
            gifsBoardInfos.add(gifsBoardInfo);
        }
        // return dto (list)
        return new GifsBoardList(gifsBoardInfos);

    }

    public MembersBoardList getMembersBoard(String username) {
        // user 반환
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException());

        // user의 board 불러오기
        List<Board> boards = member.getBoards();

        // 새 리스트 만들기
        List<MembersBoardInfo> membersBoardInfos = new ArrayList<>();

        // for문 돌면서 info dto에 add
        for(Board board : boards) {
            String thumbnailPath = null;
            if (board.getGifs().size() >= 1) {
                thumbnailPath = board.getGifs().get(0).getGifPath();
            }
            MembersBoardInfo boardInfo = new MembersBoardInfo(board.getId(), board.getBoardName(), thumbnailPath);
            membersBoardInfos.add(boardInfo);
        }
        // 생성된 리스트를 list dto의 생성자로 넘김
        return new MembersBoardList(membersBoardInfos);

    }

    public GifList getGifs(Long boardId) {
        // 보드에 포함된 애들 return
        Board board = boardRepository.findBoardById(boardId)
                .orElseThrow(() -> new NotFoundException("보드를 찾을 수 없습니다."));
        List<GifInfo> gifInfos = new ArrayList<>();
        for(Gifs gif: board.getGifs()) {
            gifInfos.add(new GifInfo(gif.getId(), gif.getGifPath()));
        }
        return new GifList(gifInfos);
    }

    public void updateBoard(String token, Long gifId, GifsBoardList gifsBoardList) {
        Member member = jwtTokenProvider.getMember(token);

        List<GifsBoardInfo> boardInfoList = gifsBoardList.getBoards();
        Gifs gif = gifsRepository.findById(gifId)
                .orElseThrow(()-> new NotFoundException("gif를 찾을 수 없습니다."));

        for (GifsBoardInfo boardInfo : boardInfoList) {
            Board repoBoard = boardRepository.findBoardById(boardInfo.getId())
                    .orElseThrow(() -> new NotFoundException("보드를 찾을 수 없습니다."));

            // 요청의 gif state와 저장된 gif state가 다르면 상태를 저장 or 삭제
            Boolean requestState = boardInfo.getGifContainState();
            if (requestState != repoBoard.getGifs().contains(gif)) {
                if (requestState == true) {
                    insertGif(repoBoard, gif, member);
                } else {
                    deleteGif(repoBoard, gif);
                }
            }
        }
        // return
    }

    private void deleteGif(Board board, Gifs gif) {
        board.getGifs().remove(gif);
        boardRepository.save(board);
    }

    private void insertGif(Board board, Gifs gif, Member member) {
        // member 회원 통계 로직 추가
        board.getGifs().add(0, gif);
        boardRepository.save(board);
    }

    public void deleteGifFromBoard(Long boardId, List<Long> gifList) {
        if (gifList.size() == 0) {
            throw new NotMatchException("gif 리스트가 비었습니다.");
        }
        Board board = boardRepository.findBoardById(boardId)
                .orElseThrow(() -> new NotFoundException("보드를 찾을 수 없습니다."));

        for (Long gifId : gifList) {
            Gifs gif = gifsRepository.findById(gifId)
                    .orElseThrow(() -> new NotFoundException("gif를 찾을 수 없습니다."));

            // real로 보드에 짤이 담겨있는지 확인
            if (!board.getGifs().contains(gif)) {
                throw new NotMatchException(gif.getId().toString() + "번 gif는 board에 담겨있지 않습니다.");
            }
            deleteGif(board, gif);
        }
    }

    public void insertGifFromBoard(String token, Long boardId, List<Long> gifIdList) {
        Member member = jwtTokenProvider.getMember(token);

        // 매서드로 분리
        if (gifIdList.size() == 0) {
            throw new NotMatchException("gif 리스트가 비었습니다.");
        }
        // 매서드로 분리
        Board board = boardRepository.findBoardById(boardId)
                .orElseThrow(() -> new NotFoundException("보드를 찾을 수 없습니다."));

        for (Long gifId : gifIdList) {
            Gifs gif = gifsRepository.findById(gifId)
                    .orElseThrow(() -> new NotFoundException(gifId.toString() +"번 gif를 찾을 수 없습니다."));

            // 이미 담겨있으면 삭제 후 저장
            if (board.getGifs().contains(gif)) {
                deleteGif(board, gif);
            }
            insertGif(board,gif, member);

        }
    }
}
