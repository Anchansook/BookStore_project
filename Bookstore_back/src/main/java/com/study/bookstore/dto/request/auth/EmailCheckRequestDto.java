package com.study.bookstore.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* 이메일 중복 확인을 위한 요청 dto */

@Getter
@Setter
@NoArgsConstructor
public class EmailCheckRequestDto {

	@NotBlank
	@Email
	private String userEmail;
	
}
