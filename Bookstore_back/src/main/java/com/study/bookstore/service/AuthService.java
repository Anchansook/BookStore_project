package com.study.bookstore.service;

import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.request.auth.EmailCheckRequestDto;
import com.study.bookstore.dto.request.auth.IdCheckRequestDto;
import com.study.bookstore.dto.response.ResponseDto;

public interface AuthService {

	// 아이디 중복 확인
	ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
	// 이메일 중복 확인
	ResponseEntity<ResponseDto> emailCheck(EmailCheckRequestDto dto);
	
}
