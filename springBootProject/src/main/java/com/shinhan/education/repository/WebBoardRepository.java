package com.shinhan.education.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.shinhan.education.vo3.QWebBoard;
import com.shinhan.education.vo3.WebBoard;

public interface WebBoardRepository 
extends PagingAndSortingRepository<WebBoard, Long>,
		QuerydslPredicateExecutor<WebBoard>{
	//인터페이스 안에 들어갈 수 있는 것
	//1. 상수
	//2. 추상메서드
	//3. default 메서드
	//4. static 메서드
	
	//동적으로 조건절 
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard;
		builder.and(board.bno.gt(0)); //and bno > 0 동적으로 들어간다. 
		//검색조건처리
		if(type==null) return builder;
		switch (type) {
		case "title": //and title like ? 
			builder.and(board.title.like("%" + keyword + "%")); break;
		case "content": //and content like ?
			builder.and(board.content.like("%" + keyword + "%")); break;
		case "writer": //and writer like ?
			builder.and(board.writer.like("%" + keyword + "%")); break;
		default: break;
		}
		return builder;
		}
	
}
