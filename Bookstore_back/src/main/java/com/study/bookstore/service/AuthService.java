package com.study.bookstore.service;

import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.request.auth.IdCheckRequestDto;
import com.study.bookstore.dto.response.ResponseDto;

public interface AuthService {

	ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
	
}
