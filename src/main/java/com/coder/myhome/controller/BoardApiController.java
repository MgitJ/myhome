package com.coder.myhome.controller;

import java.util.List;
import java.util.Optional;

import com.coder.myhome.Repository.BoardRepository;
import com.coder.myhome.Repository.BoardRepositoryJPA;
import com.coder.myhome.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepositoryJPA repository;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title
    , @RequestParam(required = false, defaultValue = "") String content) {

        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        }else{
//             return repository.findByTitle(title);
            return repository.findByTitleOrContent(title,content);
        }

    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    Board newEmployee(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item

    @GetMapping("/boards/{id}")
    Optional<Board> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/boards/{id}")
    Board replaceEmployee(@RequestBody Board newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newEmployee.getTitle());
                    board.setContent(newEmployee.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

  /*  @DeleteMapping("/boards/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }*/
}