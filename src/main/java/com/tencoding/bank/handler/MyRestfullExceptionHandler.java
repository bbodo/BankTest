package com.tencoding.bank.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.bank.handler.exception.CustomRestfullException;

@RestController // IoC 대상 + Aop 대상
@Order(1)
public class MyRestfullExceptionHandler {
	
	// 사용자 정의 예외 클래스 활용
	@ExceptionHandler(CustomRestfullException.class)
	public String basicException(CustomRestfullException e) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert( ' " + e.getMessage() +" '  );"); // 문자열 안에 반드시 ; 콜론 붙이기
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	
}
