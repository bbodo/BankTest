package com.tencoding.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String mainRedirect() {
		// 뷰 리졸버 동작.
		// prefix : /WEB-INF/view/
		// sufix  : .jsp
		return "layout/main";
		
	}  // mainRedirect() 안넣어줬을때는 localhost 입력시 
	   // index 떴는데 넣어주니깐 화면 나온다!!!
	
	// 주소 설계
	// GET lacalhost:80/main-page
	@GetMapping("/main-page")
	public String mainPage() {
		// 뷰 리졸버 동작 되야하는데...
		// prefix : /WEB-INF/view/
		// /WEB-INF/view/layout/main.jsp 
		// subfix : .jsp 
		return "layout/main";
	}
	
	

}
