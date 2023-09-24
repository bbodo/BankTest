package com.tencoding.bank.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.tencoding.bank.handler.exception.CustomPageException;

@ControllerAdvice // Ioc대상
@Order(2)
public class MyPageExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public void exception(Exception e) {
		System.out.println("==== 예외 발생 확인 ====");
		System.out.println(e.getMessage());
		System.out.println("------------------------");
	}
	
	public ModelAndView handleRuntimePageExcption(CustomPageException e) {
		// ModelAndView 활용 방법 - 페이지 명시해야 함
		 ModelAndView modelAndView = new ModelAndView("errorPage");
		 modelAndView.addObject("statusCode", HttpStatus.NOT_FOUND.value());
		 modelAndView.addObject("message", e.getMessage());
			return modelAndView;
	}

}
