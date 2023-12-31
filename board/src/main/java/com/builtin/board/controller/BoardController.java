package com.builtin.board.controller;

import com.builtin.board.entity.Board;
import com.builtin.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 글 작성 Controller
    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm() {
        return "boardwrite";
    }

    // 글 작성 처리
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file)throws Exception{

        boardService.write(board, file);

        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }

    // 글 목록
    @GetMapping("/board/list")
    public String boardlist(Model model,
    @PageableDefault(/*페이지 1~10*/page =0,size= 10,
            /*정렬 기준 - id*/sort="id",
            /*정렬 방향 역순*/ direction = Sort.Direction.DESC)
    Pageable pageable) {
        model.addAttribute("list",boardService.boardList(pageable));
        return "boardlist";
    }

    // 업로드 글 view
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) { // localhost:8080/board/view?id=1
        model.addAttribute("board",boardService.boardView(id));
        return "boardview";
    }

    // 글 삭제 처리
    @GetMapping("/board/delete")
    public String boardDelete(Integer id, RedirectAttributes rttr){
        boardService.boardDelete(id);
        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");
    return "redirect:/board/list";
    }

    // 글 수정
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model){
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    // 글 수정 처리
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board,
                              MultipartFile file,
                              RedirectAttributes rttr) throws Exception {
        Board boardTemp = boardService.boardView(id); // db에 해당 id 값을 가진 레코드를 가져옴
        boardTemp.setTitle(board.getTitle());   // 수정된 title을 set
        boardTemp.setContent(board.getContent());  // 수정된 content를 set
        boardService.write(boardTemp, file);  // db에 변경사항 write

        rttr.addFlashAttribute("message",id +"번의 게시글이 수정되었습니다.");
        return "redirect:/board/list";
    }
}
