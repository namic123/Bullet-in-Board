package com.builtin.board.controller;

import com.builtin.board.entity.Board;
import com.builtin.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, RedirectAttributes rttr){
        boardService.write(board);
        rttr.addFlashAttribute("message","게시글이 생성되었습니다.");
        return "redirect:/board/list";
    }
    @GetMapping("/board/list")
    public String boardlist(Model model) {
        model.addAttribute("list",boardService.boardList());
        return "boardlist";
    }
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) { // localhost:8080/board/view?id=1
        model.addAttribute("board",boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id, RedirectAttributes rttr){
        boardService.boardDelete(id);
        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");
    return "redirect:/board/list";
    }
}
