package com.mysite.sbb.domain.question.question.controller;

import com.mysite.sbb.domain.question.answer.AnswerForm;
import com.mysite.sbb.domain.question.question.QuestionForm;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            Model model,
            @PathVariable("id") Integer id,
            AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm, BindingResult bindingResult) {
                if (bindingResult.hasErrors()) {
                    return "question_form"; // 유효성 검증 실패시
                }
                this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 이상 없으면, 질문 목록으로 이동
    }
}