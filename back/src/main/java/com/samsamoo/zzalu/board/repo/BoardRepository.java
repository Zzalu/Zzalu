package com.samsamoo.zzalu.board.repo;


import com.samsamoo.zzalu.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long id);
}
