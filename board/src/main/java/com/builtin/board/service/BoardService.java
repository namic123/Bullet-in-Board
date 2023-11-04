package com.builtin.board.service;

import com.builtin.board.entity.Board;
import com.builtin.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void write(Board board){
        boardRepository.save(board);
    }
    public List<Board> boardList(){
       return boardRepository.findAll(); //  연결된 데이터베이스의 테이블에 저장된 모든 엔티티를 조회하는 기능
    }
}
