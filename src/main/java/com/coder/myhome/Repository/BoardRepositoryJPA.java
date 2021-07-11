package com.coder.myhome.Repository;

import com.coder.myhome.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BoardRepositoryJPA {

    private final EntityManager em;

    @Autowired
    public BoardRepositoryJPA(EntityManager em) {
        this.em = em;
    }


   public Board save(Board board) {
       if(board.getId() == null){
           em.persist(board);
       }
       else{
           em.merge(board);
       }
        return board;
    }


    public Optional<Board> findById(Long id) {
        Board board = em.find(Board.class , id);
        return Optional.ofNullable(board);
    }

    /*
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }*/

    public List<Board> findAll() {
        List<Board> result = em.createQuery("select m from Board m",Board.class).
                getResultList();
        return result;
    }
}
