package com.mysite.sbb.domain.question.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message="제목은 필수항목입니다.") // 빈 문자열("") 허용하지않음
    @Size(max=200) // 최대 글씨는 200byte
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.") // 위와같음 빈칸x
    private String content;
}