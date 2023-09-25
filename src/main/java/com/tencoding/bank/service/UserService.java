package com.tencoding.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.bank.dto.SignInFormDto;
import com.tencoding.bank.dto.SignUpFormDto;
import com.tencoding.bank.handler.exception.CustomRestfullException;
import com.tencoding.bank.repository.interfaces.UserRepository;
import com.tencoding.bank.repository.model.User;

@Service // IoC 대상 - 싱글톤 패턴
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(SignUpFormDto signUpFormDto) {

//		System.out.println("여기옴?");
		String rawPwd = signUpFormDto.getPassword();
		String hashPwd = passwordEncoder.encode(rawPwd);
		System.out.println("rawPwd : " + rawPwd);
		System.out.println("hashPwd : " + hashPwd);		
		signUpFormDto.setPassword(hashPwd);
		int result = userRepository.insert(signUpFormDto);
		System.out.println("ddddddddddddddddddd");
		if (result != 1) {
			throw new CustomRestfullException("회원가입실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 로그인 서비스 처리
	public User signIn(SignInFormDto signInFormDto) {
		
		// 계정 이름만 확인으로 변경 처리
		User userEntity = userRepository.findByUsername(signInFormDto.getUsername());

		// 계정확인
		if(userEntity == null || userEntity.getUsername().equals(signInFormDto.getUsername()) == false) {
			throw new CustomRestfullException("존재하지 않는 계정입니다.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// 비번확인
		boolean isPwdMatched = passwordEncoder
				.matches(signInFormDto.getPassword(), userEntity.getPassword());
		
		System.out.println(signInFormDto.getUsername());
		System.out.println(signInFormDto.getPassword());
		
		if(isPwdMatched == false) {
			throw new CustomRestfullException("너 잘못했어.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return userEntity;

	}

}
