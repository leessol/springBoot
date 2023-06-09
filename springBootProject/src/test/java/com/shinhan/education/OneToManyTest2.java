package com.shinhan.education;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

@SpringBootTest
//@Commit
public class OneToManyTest2 {

	@Autowired
	PDSFileRepository fileRepo;
	
	@Autowired
	PDSBoardRepository boardRepo;
	
	
	 
	//@Test
	void boardFileInsert() {
		boardRepo.findById(3L).ifPresent(board->{
			List<PDSFile> files2 = board.getFiles2();
			files2.get(1).setPdsfilename("영선$$$$$");
			PDSFile file1 = PDSFile.builder().pdsfilename("추가5!!!").build();
			PDSFile file2 = PDSFile.builder().pdsfilename("추가6!!!").build();
			files2.add(file1);files2.add(file2);
			boardRepo.save(board);
		});
	}
	
	
	//Board를 이용해서 File이름수정하기 
	//@Test
	void boardFileUpdate() {
		boardRepo.findById(3L).ifPresent(board->{
			List<PDSFile> files2 = board.getFiles2();
			files2.forEach(f->{
				f.setPdsfilename("수정!!!!~~~");
				//fileRepo.save(f);
			});
			
			boardRepo.save(board);
		});
	}
	
	//PDSBoardRepository(부모)를 이용하여 자식를 수정하기 
	//@Modifying를 사용한 경우 반드시 @Transactional 
	//실행은 성공하지만 Test환경은 Rollback처리되므로 class level에 @Commit추가 
	//Test에 @Transactional : Rollback처리한다. 
	//@Transactional
	//@Test
	void fileUpdate() {
		//boardRepo.updateFile(4L, "풍경사진");
	}
	
	
	
	//LAZY인 경우 ....자식의 접근하기위해 사용 
	//@Transactional
	//@Test
	void test7() {
		boardRepo.findAll().forEach(b->{
			System.out.println(b);
			System.out.println(b.getFiles2());
		});
		
	}
	//1-n : 부모에서 자식을 insert하자 
	//@Test
	void test6() {
		 List<PDSFile> files = new  ArrayList<PDSFile>();
		 PDSFile file1 = PDSFile.builder().pdsfilename("얼굴사진1").build();
		 PDSFile file2 = PDSFile.builder().pdsfilename("얼굴사진1").build();
		 PDSFile file3 = PDSFile.builder().pdsfilename("얼굴사진1").build();
		 files.add(file1);files.add(file2);files.add(file3);
		 
		PDSBoard board = PDSBoard.builder()
				.pname("월요일이다")
				.pwriter("진우")
				.files2(files)
				.build();
		boardRepo.save(board);
	}
	
	
	
	//'자바에서 칼럼이 없으므로 다음 방법은 불가 ......
	//@Test
	void test5() {
		
		PDSFile file = fileRepo.findById(1L).orElse(null);
		if(file!=null) {
			//PDSBoard board = boardRepo.findById(2L).orElse(null);
			file.setPdsfilename("파일이름수정");
			fileRepo.save(file);
		}
		
	}
	
	//@Test
	void test4() {
		boardRepo.findAll()
		.forEach(b->System.out.println(b));
	}
	
	//부모만 insert
	//@Test
	void test3() {
		PDSBoard board = PDSBoard.builder()
				.pname("게시글")
				.pwriter("작성자")
				.build();
		boardRepo.save(board);
	}
	
	//@Test
	void test2() {
		 
		fileRepo.findAll()
		  .forEach(f->System.out.println(f));
	}
	
	//자식만 insert
	//@Test
	void test1() {
		PDSFile file = PDSFile.builder()
				.pdsfilename("첨부파일1")
				.build();
		fileRepo.save(file);
	}
	
	
	
}
