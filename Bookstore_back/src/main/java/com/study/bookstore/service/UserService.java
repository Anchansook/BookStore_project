package com.study.bookstore.service;

import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.request.users.PatchUserRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.users.GetSingInUserResponseDto;

public interface UserService {

	// 로그인 유저 정보 확인
	ResponseEntity<? super GetSingInUserResponseDto> getSignIn(String userId);

	// 회원 수정 
	ResponseEntity<ResponseDto> patchUser(PatchUserRequestDto dto, String userId);

	// 회원 탈퇴
	ResponseEntity<ResponseDto> deleteUser(String userId);
	
}
