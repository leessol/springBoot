package com.shinhan.education.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


//@ControllerAdvice //Controller + Advice
//문제점 : static자원과 충돌
public class ExceptionControllerAdvice {
	private Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	//500오류 시 수행되는 함수. 
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model) {
		logger.error("Exception 발생 : {}", ex.getMessage());
		ex.printStackTrace(); //오류가 난 내용이 console 창에 뜬다. 
		model.addAttribute("msg", "500 처리중 에러 발생!!!" + ex.getMessage());//ex.getMessage(): 무슨 에러인지
		return "error/error500";
	}

	//404가 안먹는다. 
	//@ExceptionHandler(NoHandlerFoundException.class)
	//@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex, Model model) {
		logger.error("404 발생 : {}", "404 page not found");
		model.addAttribute("msg", "404 해당 페이지를 찾을 수 없습니다!!!");
		return "error/error404";
	}
}
