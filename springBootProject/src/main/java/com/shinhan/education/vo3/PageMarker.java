package com.shinhan.education.vo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude = "pageList")
@Log
//진짜 페이지네이션 만들기. - 전페이지는 뭐가 있고, 다음은 뭐가 있고 등등 설정하면서 만들어주는 것. 
public class PageMarker<T> {

	private Page<T> result;
	private Pageable prevPage; //이전으로 이동하는데 필요한 정보를 가짐
	private Pageable nextPage;
	private Pageable currentPage;
	private int currentPageNum;  //화면에 보이는 1부터 시작하는 페이지번호
	private int totalPageNum;
	private List<Pageable> pageList;
	
	public PageMarker(Page<T> result,int pageSize) { //몇페이지 
		this.result = result;
		this.currentPage = result.getPageable();
		this.currentPageNum = currentPage.getPageNumber()+1;
		this.totalPageNum = result.getTotalPages(); //이 만큼만 만든다. for문에서 여기까지 돌아야 함. 
		this.pageList = new ArrayList<Pageable>();
		calcPage(pageSize);
	}
	public void calcPage(int cnt) {
		int tempEndNum = (int)(Math.ceil(currentPageNum/cnt*1.0)*cnt);
		int startNum = tempEndNum - (cnt-1);//페이지를 눌렀을 때, 게시글의 시작 넘버와 끝 넘버를 알아야 한다. 
		Pageable startPage = this.currentPage;
		
		for(int i = startNum; i<this.currentPageNum; i++) {
			startPage = startPage.previousOrFirst();
		}
		this.prevPage = startPage.getPageNumber()<=0?null:startPage.previousOrFirst();
		log.info("tempEndNum:" + tempEndNum);
		log.info("totalPageNum:" + totalPageNum);
		if(this.totalPageNum<tempEndNum) {
			tempEndNum = this.totalPageNum;
			this.nextPage = null;
		}
		
		for(int i = startNum; i<=tempEndNum; i++) {
			pageList.add(startPage);
			startPage = startPage.next();
		}
		this.nextPage = startPage.getPageNumber()+1 < totalPageNum?startPage:null;
	}
}
