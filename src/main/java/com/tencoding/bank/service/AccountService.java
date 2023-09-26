package com.tencoding.bank.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.bank.dto.SaveFormDto;
import com.tencoding.bank.handler.exception.CustomRestfullException;
import com.tencoding.bank.repository.interfaces.AccountRepository;
import com.tencoding.bank.repository.model.Account;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	// 생성 로직
	@Transactional
	public void creatAccount(SaveFormDto saveFormDto, Integer principalId) {
		
//		private Integer id;
//		private String number;
//		private String password;
//		private Long balance;
//		private Integer userId;
//		private Timestamp createdAt
		
		Account account = new Account();
		System.out.println(saveFormDto.getBalance());
		account.setNumber(saveFormDto.getNumber());
		account.setPassword(saveFormDto.getPassword());
		account.setBalance(saveFormDto.getBalance());
		account.setUserId(principalId);
		
		int resultRowCounter = accountRepository.insert(account);
		if(resultRowCounter != 1) {
			throw new CustomRestfullException("계좌가 생성되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
