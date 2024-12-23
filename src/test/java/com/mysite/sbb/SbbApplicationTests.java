package com.mysite.sbb;

import com.mysite.sbb.domain.question.answer.entity.Answer;
import com.mysite.sbb.domain.question.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("findAll") // Test화면에서 보여줄 이름
	void t1() {
		List<Question> f = this.questionRepository.findAll();
		// Qeustion리스트에서 리포지토리의 findAll()을 실행시켜서 해당 값을 찾아
		assertEquals(2, f.size());

		Question q1 = f.get(0);
		assertEquals("sbb가 무엇인가요?", q1.getSubject());
	}

	@Test
	@DisplayName("findById")
	void t2() {
		Optional<Question> findById = this.questionRepository.findById(1); // 퀘스천 클래스에 있는 1번 아이디 찾아
		if (findById.isPresent()) { // 만약에 있으면,
			Question q2 = findById.get(); // 찾은 ID 를 get()해서 q변수에 저장하고
			assertEquals("sbb가 무엇인가요?", q2.getSubject());
			// 결과값이 "sbb가 무었인가요?" 대한 기대값이 q.getSubject()와 같으면 결과값 반환
			// assertEquals(기대하는 값, 결과값);
		}
	}

	@Test
	@DisplayName("findSubject")
	void t3() {
		Question q3 = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q3.getId());

	}

	@Test
	@DisplayName("findSubjectAadContent")
	void t4() {
		Question q4 = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q4.getId());
	}

	@Test
	@DisplayName("findBySubjectLike")
	void t5() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q5 = qList.get(0);
		assertEquals(1, q5.getId());
	}

	@Test
	@DisplayName("질문 데이터 수정하기")
	@Transactional
	void t6() {
		Optional<Question> opFindById = this.questionRepository.findById(1);
		assertTrue(opFindById.isPresent()); // assertTrue: 기대값이 참
		Question q = opFindById.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	@DisplayName("질문 데이터 삭제하기")
//	@Transactional
	void t7() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> opFindById = this.questionRepository.findById(1);
		assertTrue(opFindById.isPresent());
		Question q = opFindById.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}

	@Test
	@DisplayName("답변 데이터를 통해 질문 데이터 찾기")
	void t8() {
		Optional<Answer> opFindById = this.answerRepository.findById(1); // 1번 답변이 달린
		assertTrue(opFindById.isPresent());
		Answer a = opFindById.get();
		assertEquals(2, a.getQuestion().getId()); // 2번글
	}

	@Test
	@DisplayName("질문 데이터를 통해 답변 데이터 찾기")
	@Transactional // 질문 3번: Lazy가 어디서 걸리는건지
	void t9() {
		Optional<Question> qw = this.questionRepository.findById(2);
		// 질문 1번: (표현이 맞는지?) 영속성 컨텍스트에 등록?
		// 질문 2번: 왜 이걸 영속성 컨텍스트로 등록해야만 하나?
		// 영속성: 데이터를 생성한 프로그램이 종료되어도 사라지지 않는 데이터의 특성
		assertTrue(qw.isPresent());
		Question q = qw.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}
