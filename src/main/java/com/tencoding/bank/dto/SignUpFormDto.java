package com.tencoding.bank.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignUpFormDto {
	private String username; 
	private String password;
	private String fullname;

}
