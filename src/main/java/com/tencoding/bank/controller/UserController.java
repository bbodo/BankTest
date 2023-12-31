package com.tencoding.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.bank.dto.SignInFormDto;
import com.tencoding.bank.dto.SignUpFormDto;
import com.tencoding.bank.handler.exception.CustomRestfullException;
import com.tencoding.bank.repository.model.User;
import com.tencoding.bank.service.UserService;
import com.tencoding.bank.utils.Define;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	
	// 회원 가입 페이지 요청
	// http://localhost:80/user/sign-up
	@GetMapping("/sign-up")
	public String signUp() {

		return "user/signUp";
	}

	// 로그인 페이지 요청
	// http://localhost:80/user/sign-in
	@GetMapping("/sign-in")
	public String signIn() {

		return "user/signIn";
	}
	
	@GetMapping("/logout")
	public String logout() {

		return "redirect:/user/sign-in";
	}

	/**
	 * 회원 가입 처리
	 * @param signUpFormDto
	 * @return
	 */
	@PostMapping("/sign-up")
	public String signUpProc(SignUpFormDto signUpFormDto) {
		// 1. 유효성 검사
		if(signUpFormDto.getUsername() == null
				|| signUpFormDto.getUsername().isEmpty()) {
			throw new CustomRestfullException("username을 입력하세요.",
					HttpStatus.BAD_REQUEST);
		}
		if(signUpFormDto.getPassword() == null
				|| signUpFormDto.getPassword().isEmpty()) {
			throw new CustomRestfullException("password를 입력하세요",
					HttpStatus.BAD_REQUEST);
		}
		if(signUpFormDto.getFullname() == null
				|| signUpFormDto.getFullname().isEmpty()) {
			throw new CustomRestfullException("fullname을 입력하세요",
					HttpStatus.BAD_REQUEST);
		}
		System.out.println("여기오나");
		
		// 로직 추가 -- 서비스 호출
		userService.signUp(signUpFormDto);

		return "redirect:/user/sign-in";
	}

	@PostMapping("/sign-in")
	public String signInProc(SignInFormDto signInFormDto) {
		// 1. 유효성 검사
		if(signInFormDto.getUsername() == null) {
			throw new CustomRestfullException("username을 입력하세요.", HttpStatus.BAD_REQUEST);
		}
		if(signInFormDto.getPassword() == null) {
			throw new CustomRestfullException("password를 입력하세요", HttpStatus.BAD_REQUEST);
		}
		
		// 2. 서비스 -> 인증된 사용자 여부 확인
		// principal <-- 접근 주체
		User principal = userService.signIn(signInFormDto);
		principal.setPassword(null);
		// 3. 쿠기 + 세션 
		session.setAttribute(Define.PRINCIPAL, principal);
		
		return "redirect:/account/list";
	}

}
