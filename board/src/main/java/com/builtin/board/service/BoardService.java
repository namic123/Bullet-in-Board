package com.builtin.board.service;

import com.builtin.board.entity.Board;
import com.builtin.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void write(Board board){
        boardRepository.save(board);
    }
}
