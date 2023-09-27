package com.tencoding.bank.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Account {
	private Integer id;
	private String number;
	private String password;
	private Long balance;
	private Integer userId;
	private Timestamp createdAt;
	
	
	// 출금 기능
	public void withdraw(Long amount) {
		if (this.balance > amount)
			this.balance -= amount;
	}
	

	
	
	
}