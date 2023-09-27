package com.tencoding.bank.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.bank.dto.DepositFormDto;
import com.tencoding.bank.dto.SaveFormDto;
import com.tencoding.bank.dto.WithdrawFormDto;
import com.tencoding.bank.handler.MyRestfullExceptionHandler;
import com.tencoding.bank.handler.exception.CustomRestfullException;
import com.tencoding.bank.repository.interfaces.AccountRepository;
import com.tencoding.bank.repository.interfaces.HistoryRepository;
import com.tencoding.bank.repository.model.Account;
import com.tencoding.bank.repository.model.History;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private HistoryRepository historyRepository;
	

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

	public List<Account> readAccountList(Integer userId) {
		
		List<Account> list = accountRepository.findByUserId(userId);
		return list;
	}

	// 출금 기능 로직을 고민보기 
		// 1. 계좌 존재 여부 확인 -- select query 
		// 2. 본인 계좌 여부 확인 
		// 3. 계좌 비번 확인 
		// 4. 잔액 여부 확인 
		// 5. 출금 처리 --> update query 
		// 6. 거래 내역 등록 --> insert query 
		// 7. 트랜잭션 처리 
	@Transactional
	public void updateAccountWithdraw(WithdrawFormDto withdrawFormDto, Integer id) {
		System.out.println("2번 오세요");
		Account accountEnitity = accountRepository.findByNumber(withdrawFormDto.getWAccountNumber());
		// 1
		if(accountEnitity == null) {
			throw new CustomRestfullException("해당 계좌가 존재하지 않습니다", HttpStatus.BAD_REQUEST);
		}
		// 2
		if(accountEnitity.getUserId() != id) {
			throw new CustomRestfullException("계좌의 소유자가 아닙니다", HttpStatus.BAD_REQUEST);
		}
		// 3
		if(accountEnitity.getPassword().equals(withdrawFormDto.getWAccountPassword()) == false) {
			throw new CustomRestfullException("계좌의 소유자가 아닙니다", HttpStatus.BAD_REQUEST);
		}
		if(accountEnitity.getBalance() < 0) {
			throw new CustomRestfullException("잔액이 없습니다", HttpStatus.BAD_REQUEST);
		}
		
		accountEnitity.withdraw(withdrawFormDto.getAmount());
		accountRepository.updateById(accountEnitity);
		
		History history = new History(); 

//		private Long amount; 
//		private Long wBalance; 
//		private Long dBalance; 
//		private Integer wAccountId; 
//		private Integer dAccountId; 
		
        // 거래내역
		history.setAmount(withdrawFormDto.getAmount());
		// 출금시점
		history.setWBalance(accountEnitity.getBalance());
		history.setDBalance(null);
		history.setWAccountId(accountEnitity.getId());
		history.setDAccountId(null);
		
		int resultRowCount = historyRepository.insert(history);
		  if(resultRowCount != 1) {
			  throw new CustomRestfullException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	// 계좌 존재 확인 -> select
	// 입금 처리 -> 
	// 거래 내역 ->
	public void updateAccountDeposit(DepositFormDto depositFormDto) {
		
//		Account accountEntity = accountRepository.findByNumber(depositFormDto);
	}
	
	
	

}
