package com.tenconding.bank.contorller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

	// 주소 설계
	// GET lacalhost:80/main-page
	@GetMapping("main-page")
	public String mainPage() {
		// 뷰 리졸버 동작 되야하는데...
		// prefix : /WEB-INF/view/
		// /WEB-INF/view/layout/main.jsp 
		// subfix : .jsp 
		return "layout/main";
	}

}
