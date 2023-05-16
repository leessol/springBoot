package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

@SpringBootTest
public class OneToManyTestSol {

	@Autowired
	PDSFileRepository fileRepo;

	@Autowired
	PDSBoardRepository boardRepo;

	//1-n: 부모에서 자식을 insert하자 
	@Test
	void test6() {
		
	}
	
	
	//자바에서 컬럼이 없으므로 다음 방법은 불가.... 
	//@Test
	void test5() {
		PDSFile file = fileRepo.findById(1L).orElse(null);
		if(file!=null) {
			PDSBoard board =  boardRepo.findById(2L).orElse(null);
			
			fileRepo.save(file);
		}
		
	}

	// @Test
	void test4() {
		boardRepo.findAll().forEach(b -> System.out.println(b));
	}

	// 부모만 insert
	// @Test
	void test3() {
		PDSBoard board = PDSBoard.builder().pname("게시글").pwriter("작성자").build();
		boardRepo.save(board);
	}
}
