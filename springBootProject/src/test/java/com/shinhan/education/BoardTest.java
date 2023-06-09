package com.shinhan.education;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.vo.BoardVO;
import com.shinhan.education.vo.CarVO;
import com.shinhan.education.vo.QBoardVO;

import lombok.extern.java.Log;

//JUNIT으로 단위test하기 
@SpringBootTest
@Log
class BoardTest {
	Logger logger = LoggerFactory.getLogger(BoardTest.class);

	@Autowired
	BoardRepository brepo;
	
	
	@Test
	void dynamicSQLTest() {
		String title = "제목9"; //where title like '%제목9%' 
		Long bno = 110L; //where bno>150 
		// 위의 조건들이 동적으로 만들어졌으면 좋겠다. 
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoardVO board = QBoardVO.boardVO;
		builder.and(board.title.like("%" + title + "%"));
		builder.and(board.boardNo.gt(bno));
		builder.and(board.writer.eq("작성자1"));
		
		System.out.println(builder);
		
		//findAll() => CrudRepository
		//findAll(predicate) => QuerydslPredicateExecutor에서 제공
		List<BoardVO> blist = (List<BoardVO>) brepo.findAll(builder);
		blist.forEach(b->{
			log.info(b.toString());
		});
	}
	
	
	//@Test
	void sampleNativeQueryTest() {
		List<Object[]> blist = brepo.findByTitle5("89","내용");
		blist.forEach(b -> {
			log.info(b.toString());
		});
		
	}
	
	//@Test
	void sample6() {
		List<BoardVO> blist = brepo.findByTitle2("89","내용");
		blist.forEach(board -> {
			log.info(board.toString());
		});
		
	}
	
	//@Test --> 안됨
	void sample5() {
		
		//Sort sort = Sort.by("bno").descending();
		
		//order by writer DESC, bno DESC
		Sort sort = Sort.by(Sort.Direction.DESC, new String[] {"writer","bno"});
		Pageable paging = PageRequest.of(0, 5, sort); // (page,size) -> (몇페이지, 한페이지 사이즈)
		
		Page<BoardVO> result = brepo.findByboardNoGreaterThan(110L,paging);
		log.info("페이지당 건수: " + result.getSize());
		log.info("페이지 총 수: " + result.getTotalPages());
		log.info("전체 건수: " + result.getTotalElements());
		log.info("다음 페이지 정보: " + result.nextPageable());
		
//		List<BoardVO> blist = result.getContent()); 
//		blist.forEach(board -> {
//		log.info(board.toString()); 
//		});
		 
	}
	
	
	//@Test
	void sample4() {
		
		//Sort sort = Sort.by("bno").descending();
		
		//order by writer DESC, bno DESC
		Sort sort = Sort.by(Sort.Direction.DESC, new String[] {"writer","bno"});
		Pageable paging = PageRequest.of(2, 5, sort); // (page,size) -> (몇페이지, 한페이지 사이즈)
		List<BoardVO> blist = brepo.findByTitleContaining("제목",paging);
		blist.forEach(board -> {
			log.info(board.toString());
		});
	}
	
	//@Test
	void sample3() {
		Pageable paging = PageRequest.of(1, 5); // (page,size) -> (몇페이지, 한페이지 사이즈)
		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목",paging);
		blist.forEach(board -> {
			log.info(board.toString());
		});
	}

	// @Test
	void sample2() {
		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목");
		blist.forEach(board -> {
			log.info(board.toString());
		});
	}

	// @Test
	void sample1() {
		long rowCount = brepo.count();
		log.info(rowCount + "건");

		boolean result = brepo.existsById(110L);
		log.info(result ? "존재한다" : "존재하지 않는다. ");
	}

	// @Test
	public void retrieve12() {
		List<BoardVO> blist = brepo.findByContentIgnoreCase("abc");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve11() {
		List<BoardVO> blist = brepo.findByContentIsNull();
		blist.forEach(board -> {
			System.out.println(board);
			if (board.getBoardNo() % 2 == 0) {
				board.setContent("ABC");
			} else {
				board.setContent("abc");
			}

			brepo.save(board);
		});
	}

	// @Test
	public void retrieve10() {
		List<BoardVO> blist = brepo.findByBoardNoBetweenOrderByBoardNoDesc(190L, 200L);
		blist.forEach(board -> {
			System.out.println(board);
			board.setContent(null);
			brepo.save(board);
		});
	}

	// @Test
	public void retrieve9() {
		List<BoardVO> blist = brepo.findByTitleContainingAndBoardNoGreaterThanEqual("제목", 190L);
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve8() {
		List<BoardVO> blist = brepo.findByTitleNotLike("%제목수정%");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve7() {
		List<BoardVO> blist = brepo.findByTitleEndingWith("2");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve6() {
		List<BoardVO> blist = brepo.findByTitleStartingWith("게시");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve5() {
		List<BoardVO> blist = brepo.findByTitleContaining("제목1");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve4() {
		List<BoardVO> blist = brepo.findByWriterAndTitle("작성자9", "게시글제목19");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve3() {
		List<BoardVO> blist = brepo.findByWriter("작성자9");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve2() {
		List<BoardVO> blist = brepo.findByContent("게시글내용....20");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void retrieve1() {
		List<BoardVO> blist = brepo.findByTitle("제목수정");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void test7() {
		Class<?> bClass = brepo.getClass();
		System.out.println(bClass.getName());
		Class<?>[] interfaces = bClass.getInterfaces();
		Stream.of(interfaces).forEach(inter -> {
			System.out.println(inter.getName());
		});
		System.out.println("--------");
		System.out.println(bClass.getSuperclass().getName());

	}

	// @Test
	public void test6() {
		BoardVO board = brepo.findById(101L).orElse(null);
		if (board != null) {
			board.setContent("내용수정");
			board.setTitle("제목수정");
			board.setWriter("admin");
			brepo.save(board); // 이미있는 데이터는 update
		}
	}

	// @Test
	public void test5() {
		BoardVO board = brepo.findById(101L).orElse(null);
		System.out.println(board);
	}

	// @Test
	public void test4() {
		brepo.findAll().forEach(board -> {
			System.out.println(board);
		});

	}

	// @Test
	public void test3() {

		IntStream.rangeClosed(1, 100).forEach(i -> {
			BoardVO board = BoardVO.builder().title("게시글제목" + i).content("게시글내용...." + i).writer("작성자" + (i % 10))
					.build();
			brepo.save(board); // save함수에 insert된다.
		});

	}

	// @Test
	void test2() {
		CarVO car1 = CarVO.builder().model("B모델").price(3000).build();
		logger.info(car1.toString());
	}

	// @Test
	void test1() {
		CarVO car1 = new CarVO();
		car1.setModel("A모델");
		car1.setPrice(1000);

		CarVO car2 = new CarVO();
		car2.setModel("A모델");
		car2.setPrice(2000);

		logger.info(car1.toString());
		logger.info("같은가?" + car1.equals(car2));

	}

	// @Test
	void contextLoads() {
	}

}
