package com.mysite.sbb.domain.question.question.repository;

import com.mysite.sbb.domain.question.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Question: 이 클래스로 다룰 엔티티 클래스와 연동된 테이블
// Integer : 해당클래스의 주키(Primary Key)

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);
}
