package com.study.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.bookstore.dto.request.auth.EmailCheckRequestDto;
import com.study.bookstore.dto.request.auth.IdCheckRequestDto;
import com.study.bookstore.dto.request.auth.SignUpRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	//* 아이디 중복 확인 */
	@PostMapping("/id-check")
	public ResponseEntity<ResponseDto> idCheck(
		@RequestBody @Valid IdCheckRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
		return response;
	};

	//* 이메일 중복 확인 */
	@PostMapping("/email-check")
	public ResponseEntity<ResponseDto> emailCheck(
		@RequestBody @Valid EmailCheckRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.emailCheck(requestBody);
		return response;
	};

	//* 회원가입 */
	@PostMapping("sign-up")
	public ResponseEntity<ResponseDto> signUp(
		@RequestBody @Valid SignUpRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
		return response;
	};
	
}
