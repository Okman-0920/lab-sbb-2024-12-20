package com.mysite.sbb.domain.question.question.entity;

import com.mysite.sbb.domain.question.answer.entity.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {

    @Id // 이 필드가 엔티티의 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본기의 값을 자동으로 생성하는 전략(strategy)을 설정
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> AnswerList;
}
