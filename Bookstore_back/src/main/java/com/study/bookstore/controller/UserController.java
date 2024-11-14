package com.study.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.bookstore.dto.response.users.GetSingInUserResponseDto;
import com.study.bookstore.service.UserService;

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
	
}
