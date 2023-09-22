package com.tenconding.bank.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenconding.bank.repository.model.Account;

@Mapper
public interface AccountRepository {
	
	public int insert(Account account);
	public int updateById(Account account);
	public int deleteBtId(Integer id);
	
	public List<Account> findAll();
	public Account findById(Integer id);
	
	public List<Account> findbyUserId(Integer principalId);
	public Account findByNumber(String number); // 계좌번호로 계좌 존재 여부 확인
	
	
}
