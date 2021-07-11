package com.coder.myhome.controller;

import com.coder.myhome.Repository.BoardRepository;
import com.coder.myhome.Repository.BoardRepositoryJPA;
import com.coder.myhome.model.Board;
import com.coder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    /*@Autowired
    private BoardRepository boardRepository;*/

     @Autowired
     private BoardRepositoryJPA boardRepositoryJPA;

     @Autowired
     private BoardValidator boardValidator;

     @GetMapping("/list")
     public String list(Model model){ //파라메터값을 넘겨주고싶다면 model
         List<Board> boards = boardRepositoryJPA.findAll();
         model.addAttribute("boards",boards);
         return "board/list";
     }

     @GetMapping("/form")
     public String form(Model model, @RequestParam(required = false) Long id){ //필요할 때만 받을 수 있게
         if(id == null){
             model.addAttribute("board",new Board());  //객체를 보낸다.
         }else{
             Board board = boardRepositoryJPA.findById(id).orElse(null);
             model.addAttribute("board",board);
         }
         return "board/form";
     }

     @PostMapping("/form")
     public String formSubmit(@Valid Board board, BindingResult bindingResult){

         boardValidator.validate(board, bindingResult);

         if(bindingResult.hasErrors()){
             return "board/form";
         }

         boardRepositoryJPA.save(board);
         return "redirect:/board/list";
     }



}
