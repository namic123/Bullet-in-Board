package com.builtin.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // DB를 자바 객체로서 사용하기 위한 JPA 메너테이션
@Data
public class Board {
    @Id // PK임을 지정
    //Java Persistence API(JPA)의 어노테이션으로,
    // 주로 엔티티의 기본 키(primary key)를 위한 값 생성 전략을 명시할 때 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB
    private Integer id;
    private String title;
    private String content;
}
