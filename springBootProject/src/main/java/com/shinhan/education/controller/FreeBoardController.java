package com.shinhan.education.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.education.repository.FreeBoardRepository;
import com.shinhan.education.repository.FreeRepliesRepository;

@Controller
public class FreeBoardController {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeRepliesRepository replyRepo;
	
	@GetMapping("/freeboard/elTest")
	public void freeboard3(Model dataScope, HttpSession session) {
		dataScope.addAttribute("boardList",boardRepo.findAll());
		dataScope.addAttribute("now",new Date());
		dataScope.addAttribute("price",123456789);
		dataScope.addAttribute("title","this is just a sample");
		dataScope.addAttribute("options",Arrays.asList("apple","banana","kiwi"));
		
		session.setAttribute("userName","이솔");
	}
	
	//전부 조회
	@GetMapping("/freeboard/selectAll")
	public String freeboard2(Model dataScope) {
		dataScope.addAttribute("boardList",boardRepo.findAll());
		return "freeboard/list.html";
	}
	
	@GetMapping("/freeboard/detail")
	public void freeboard1(/*@RequestParam("bno") 생략가능-이름이 같게 쓰면 */ Long bno, Model model) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board", board);
		});
	}
	
	@GetMapping("/aa/firstzone3")//return 안함. 그러면 html 파일 명과 반드시 같아야 한다. 
	public void test3(Model model) { 
		model.addAttribute("greeting", "OK!!");
		model.addAttribute("company", "국민은행"); 
	}
	
	@GetMapping("/firstzone2")//return 안함. 그러면 html 파일 명과 반드시 같아야 한다. 
	public String test2(Model model) { 
		model.addAttribute("greeting", "하이~~");
		model.addAttribute("company", "신한금융"); 
		return "firstzone1"; //페이지 forward -> templates/firstzone1.html
		
	}
	
	@GetMapping("/firstzone1")//return 안함. 그러면 html 파일 명과 반드시 같아야 한다. 
	public void test1(Model model) { 
		model.addAttribute("greeting", "하이~~");
		model.addAttribute("company", "신한금융"); 
		
	}
}
