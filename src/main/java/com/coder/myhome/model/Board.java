package com.coder.myhome.model;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Data   //getter,setter를 lombok을 이용하여 외부에서 꺼내쓸 수 있게
@Entity //db연결을 위한 model클래스임을 알림.
public class Board {  //table 이름과 같아야 한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "제목을 바르게 입력하세요.")
    private String title;
    private String content;
}
