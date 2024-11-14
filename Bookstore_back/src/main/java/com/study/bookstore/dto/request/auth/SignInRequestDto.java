package com.study.bookstore.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* 로그인 요청을 위한 dto */

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {

	@NotBlank
	private String userId;
	@NotBlank
	@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
	private String userPassword;
	
}
