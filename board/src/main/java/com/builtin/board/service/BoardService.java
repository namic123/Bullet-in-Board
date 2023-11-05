package com.builtin.board.service;

import com.builtin.board.entity.Board;
import com.builtin.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws IOException {
       // Board 엔티티의 파일 저장 로직

        // System.getProperty() - 시스템 속성 값을 검색
        // user.dir 속성 - 애플리케이션이 실행되고 있는 디렉토리의 절대 경로
        //"\\src\\main\\resources\\static\\files"; - 파일이 저장될 경로
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        // UUID (Universally Unique Identifier) - 고유한 이름을 생성
        UUID uuid = UUID.randomUUID();
        // uuid(고유한 값) + _ +기본 파일이름을 저장
        String fileName = uuid +"_"+file.getOriginalFilename();

        // 파일 경로와 이름을 지정
        File saveFile = new File(projectPath,fileName);
        // MultipartFile.transferTo - 업르드된 파일을 지정된 위치에 저장
        file.transferTo(saveFile);

        // board 엔티티를 DB에 저장
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

    //특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

}
