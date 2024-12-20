package com.mysite.sbb;

import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	@DisplayName("findAll") // Test화면에서 보여줄 이름
	void t1() {
		List<Question> f = this.questionRepository.findAll();
		// Qeustion리스트에서 리포지토리의 findAll()을 실행시켜서 해당 값을 찾아
		assertEquals(2, f.size());

		Question q = f.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("findById")
	void t2() {
		Optional<Question> findById = this.questionRepository.findById(1); // 퀘스천 클래스에 있는 1번 아이디 찾아
		if (findById.isPresent()) { // 만약에 있으면,
			Question q = findById.get(); // 찾은 ID 를 get()해서 q변수에 저장하고
			assertEquals("sbb가 무엇인가요?", q.getSubject());
			// 결과값이 "sbb가 무었인가요?" 대한 기대값이 q.getSubject()와 같으면 결과값 반환
			// assertEquals(기대하는 값, 결과값);
		}
	}

	@Test
	@DisplayName("findSubject")
	void t3() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());

	}

}
