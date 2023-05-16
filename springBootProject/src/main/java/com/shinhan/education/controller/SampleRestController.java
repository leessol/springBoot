package com.shinhan.education.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.vo.BoardVO;
import com.shinhan.education.vo.CarVO;
import com.shinhan.education.vo.QBoardVO;

import lombok.extern.java.Log;

@RestController //@Controller + @ResponseBody
				//response.getWriter().append("jsp/servlet")
@Log
public class SampleRestController {
	
	@Autowired
	BoardRepository brepo;
	
//	@GetMapping("/sunday")
//	void dynamicSQLTest() {
//		String title = "제목9"; //where title like '%제목9%' 
//		Long bno = 110L; //where bno>150 
//		// 위의 조건들이 동적으로 만들어졌으면 좋겠다. 
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		QBoardVO board = QBoardVO.boardVO;
//		builder.and(board.title.like("%" + title + "%"));
//		builder.and(board.boardNo.gt(bno));
//		builder.and(board.writer.eq("작성자1"));
//		
//		System.out.println(builder);
//		
//		//findAll() => CrudRepository
//		//findAll(predicate) => QuerydslPredicateExecutor에서 제공
//		List<BoardVO> blist = (List<BoardVO>) brepo.findAll(builder);
//		blist.forEach(b->{
//			log.info(b.toString());
//		});
//		
//	}
	
	@GetMapping("/friday")
	Map<String, Object>sample1(){
    	long rowCount = brepo.count();
    	log.info(rowCount + "건");
    	
    	boolean result = brepo.existsById(110L);
    	log.info(result?"존재한다":"존재하지 않는다. ");
    	
    	Map<String, Object> map = new HashMap<>();
    	map.put("aa", rowCount + "건");
    	map.put("bb", result?"존재한다":"존재하지 않는다. ");
    	map.put("data", brepo.findById(110L).orElse(null));
    	return map;
	}
		
	@GetMapping("/cartest2")
	public List<CarVO> test4() { //Jackson이 JAVA 객체를 JSON으로 만들어서 return 해준다. 
		List<CarVO> carlist = new ArrayList<>();
		
		IntStream.rangeClosed(1, 10).forEach(index->{
			CarVO car1 = CarVO.builder()
					.model("BMW --- " + index)
					.price(6000)
					.build();
			carlist.add(car1);
		});
		
		
		return carlist; //jackson에 의해서  json으로 만들어준다. 
	}
	
	
	
	//@RequestMapping(value = "/sample1", method = RequestMethod.GET)
	@GetMapping("/sample1")
	public String test1() {
		return "StringBoot 열공";
	}
	
	@GetMapping("/sample2")
	public String test2() {
		return "StringBoot 안녕";
	}
	
	@GetMapping("/cartest")
	public CarVO test3() { //Jackson이 JAVA 객체를 JSON으로 만들어서 return 해준다. 
		CarVO car1 = CarVO.builder()
				.model("BMW")
				.price(6000)
				.build();
		return car1; //jackson에 의해서  json으로 만들어준다. 
	}
}
