package com.tencoding.bank.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	// 회원 가입 페이지 요청
	// http://localhost:80/user/sign-up
	@GetMapping("/sign-up")
	public String signUp() {
		
		return "user/signUp";
	}
	
	// 로그인 페이지 요청 
	// http://localhost:80/user/sign-in

}
