package com.study.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.bookstore.dto.request.users.PatchUserRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.users.GetSingInUserResponseDto;
import com.study.bookstore.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// 로그인 유저 정보 확인
	@GetMapping("/sign-in-info")
	public ResponseEntity<? super GetSingInUserResponseDto> getSignIn(
		@AuthenticationPrincipal String userId
	) {
		ResponseEntity<? super GetSingInUserResponseDto> response = userService.getSignIn(userId);
		return response;
	};

	// 회원 수정
	@PatchMapping("/patch-info")
	public ResponseEntity<ResponseDto> patchUser(
		@RequestBody @Valid PatchUserRequestDto requestBody,
		@AuthenticationPrincipal String userId
	) {
		ResponseEntity<ResponseDto> response = userService.patchUser(requestBody, userId);
		return response;
	};
	
}
