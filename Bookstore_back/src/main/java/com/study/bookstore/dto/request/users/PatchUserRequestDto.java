package com.study.bookstore.dto.request.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* 회원 수정을 위한 요청 dto */

@Getter
@Setter
@NoArgsConstructor
public class PatchUserRequestDto {

	@NotBlank
	private String userName;
	@NotBlank
	@Email
	private String userEmail;
	
}
