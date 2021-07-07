package com.coder.myhome.controller;

import com.coder.myhome.Repository.BoardRepository;
import com.coder.myhome.Repository.BoardRepositoryJPA;
import com.coder.myhome.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    /*@Autowired
    private BoardRepository boardRepository;*/

    @Autowired
    private BoardRepositoryJPA boardRepositoryJPA;

     @GetMapping("/list")
     public String list(Model model){ //파라메터값을 넘겨주고싶다면 model

         List<Board> boards = boardRepositoryJPA.findAll();
         model.addAttribute("boards",boards);
         return "board/list";
     }
}
