package com.study.bookstore.service;

import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.response.users.GetSingInUserResponseDto;

public interface UserService {

	// 로그인 유저 정보 확인
	ResponseEntity<? super GetSingInUserResponseDto> getSignIn(String userId);
	
}
