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
    // 글 작성 처리
    public void write(Board board){
        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public List<Board> boardList(){
       return boardRepository.findAll(); //  연결된 데이터베이스의 테이블에 저장된 모든 엔티티를 조회하는 기능
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }
}
