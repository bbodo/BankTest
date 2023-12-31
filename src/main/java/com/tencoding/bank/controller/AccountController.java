package com.tencoding.bank.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.bank.dto.DepositFormDto;
import com.tencoding.bank.dto.SaveFormDto;
import com.tencoding.bank.dto.TransferFormDto;
import com.tencoding.bank.dto.WithdrawFormDto;
import com.tencoding.bank.handler.exception.CustomRestfullException;
import com.tencoding.bank.handler.exception.UnAuthorizedException;
import com.tencoding.bank.repository.model.Account;
import com.tencoding.bank.repository.model.User;
import com.tencoding.bank.service.AccountService;
import com.tencoding.bank.utils.Define;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService; 

	@Autowired
	private HttpSession session;

	// 계좌 목록페이
	// http://localhost:80/account/list
	@GetMapping("/list")
	public String list(Model model) {
		// 1. 인증 여부 확인
		User user = (User)session.getAttribute(Define.PRINCIPAL);

		//		if(user == null) {
		//			throw new UnAuthorizedException("로그인 후 이용해주세요", HttpStatus.UNAUTHORIZED);
		// 얘 있으면 오류뜸	 왜지?

		List<Account> accountList = accountService.readAccountList(user.getId());

		if(accountList.isEmpty()) {
			model.addAttribute("accountList", null);
		} else {
			model.addAttribute("accountList", accountList);

		}

		return "account/list";
	}


	// 계좌 생성 페이지
	// http://localhost:80/account/save
	@GetMapping("/save")
	public String save() {
		// 1. 인증 여부 확인
		User user = (User)session.getAttribute("principal");
		if(user == null) {
			throw new UnAuthorizedException("로그인 먼저 해요", HttpStatus.UNAUTHORIZED);
		}
		return "account/save";
	}


	@PostMapping("/save")
	public String saveProc(SaveFormDto saveFormDto) {

		// 1. 인증 여부 확인
		User user = (User)session.getAttribute("principal");
		if(user == null) {
			throw new UnAuthorizedException("로그인 먼저 해요", HttpStatus.UNAUTHORIZED);
		}

		// 2. 유효성? 확인
		if(saveFormDto.getNumber() == null ||
				saveFormDto.getNumber().isEmpty()) {
			throw new CustomRestfullException("계좌 번호를 설정해주세요",HttpStatus.BAD_REQUEST );
		} else {
			//			System.out.println("넘버는 되나");
		}
		if(saveFormDto.getPassword() == null ||
				saveFormDto.getPassword().isEmpty()) {
			throw new CustomRestfullException("비밀번호를 설정해주세요", HttpStatus.BAD_REQUEST);
		} else {
			//			System.out.println("패스워드는 되나");
		}

		if(saveFormDto.getBalance() == null ||
				saveFormDto.getBalance() < 0) {
			throw new CustomRestfullException("계좌 잔액을 0원 보다 많게 입력해주세요", HttpStatus.BAD_REQUEST);
		} else {
			//			System.out.println("얘가 안되는거같음");
		}

		// 생성 기능?쨋든 서비스 ? 호출 해야하지않나..?
		accountService.creatAccount(saveFormDto, user.getId()); // getId()인것도 모르는 놈


		return "redirect:/account/list";
	}

	@GetMapping("/withdraw")
	public String withdraw() {

		return "account/withdraw";
	}

	// 출금 페이지
	// http://localhost:80/account/withdraw
	@PostMapping("/withdraw")
	public String withdrawProc(WithdrawFormDto withdrawFormDto) {
		//		System.out.println("오세요");
		// 1. 인증 여부 확인
		User user = (User)session.getAttribute("principal");
		if(user == null) {
			throw new UnAuthorizedException("로그인 먼저 해요", HttpStatus.UNAUTHORIZED);
		}

		// 유효성
		if(withdrawFormDto.getAmount() == null) {
			throw new CustomRestfullException("금액을 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		if(withdrawFormDto.getAmount() < 0) {
			throw new CustomRestfullException("0원 이하는 출금 할 수 없습니다", HttpStatus.BAD_REQUEST);
		}
		if(withdrawFormDto.getWAccountNumber() == null) {
			throw new CustomRestfullException("계좌 번호를 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		if(withdrawFormDto.getWAccountPassword() == null) {
			throw new CustomRestfullException("계좌 비밀 번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
		}

		System.out.println("1번 오세요");
		// 기능
		accountService.updateAccountWithdraw(withdrawFormDto, user.getId());

		return "redirect:/account/list";
	}



	// 입금 페이지 
	// http://localhost/account/deposit
	@GetMapping("/deposit")
	public String deposit() {
		return "account/deposit";
	}

	@PostMapping("/deposit")
	public String depositProc(DepositFormDto depositFormDto) {
		// 1. 인증 여부 확인
		User user = (User)session.getAttribute("principal");
		if(user == null) {
			throw new UnAuthorizedException("로그인 먼저 해요", HttpStatus.UNAUTHORIZED);
		}
		
		// 유효성
		if(depositFormDto.getDAccountNumber() == null) {
			throw new CustomRestfullException("입금 받을 계좌번호를 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		if(depositFormDto.getAmount() < 0) {
			throw new CustomRestfullException("입금 할 금액은 0원보다 많아야 합니다", HttpStatus.BAD_REQUEST);
		}
		if(depositFormDto.getAmount() == null) {
			throw new CustomRestfullException("입금 할 금액을 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		
		// 입금 
		accountService.updateAccountDeposit(depositFormDto);
		

		return "";
	}







}
