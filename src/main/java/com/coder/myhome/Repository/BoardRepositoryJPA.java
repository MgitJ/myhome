package com.coder.myhome.Repository;

import com.coder.myhome.model.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositoryJPA {

    private final EntityManager em;

    public BoardRepositoryJPA(EntityManager em) {
        this.em = em;
    }


   /* public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class , id);
        return Optional.ofNullable(member);
    }

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
