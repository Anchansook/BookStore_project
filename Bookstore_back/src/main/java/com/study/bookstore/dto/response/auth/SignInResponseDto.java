package com.study.bookstore.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.response.ResponseCode;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.ResponseMessage;

import lombok.Getter;

//* 로그인 시 응답 dto */

@Getter
public class SignInResponseDto extends ResponseDto {

	private String accessToken;
	private Integer expiration;

	private SignInResponseDto(String accessToken) {

		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.accessToken = accessToken;
		this.expiration = 10 * 60 * 60;

	};

	public static ResponseEntity<? super SignInResponseDto> success(String accessToken) {
		SignInResponseDto responseBody = new SignInResponseDto(accessToken);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	};
	
}
