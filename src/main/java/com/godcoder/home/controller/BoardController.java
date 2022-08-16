package com.godcoder.home.controller;

import com.godcoder.home.model.Board;
import com.godcoder.home.repository.BoardRepostiory;
import com.godcoder.home.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepostiory boardRepostiory;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boards = boardRepostiory.findAll();
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {

        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepostiory.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String write(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()) {

            return "board/form";
        }

        boardRepostiory.save(board);

        return "redirect:/board/list";
    }
}
