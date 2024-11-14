package com.study.bookstore.dto.response.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.response.ResponseCode;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.ResponseMessage;
import com.study.bookstore.entity.UsersEntity;

import lombok.Getter;

//* 로그인 유저 정보 확인에 대한 응답 dto */

@Getter
public class GetSingInUserResponseDto extends ResponseDto {

	private String userName;
	private String userId;
	private String usreEmail;

	public GetSingInUserResponseDto(UsersEntity usersEntity) {

		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.userName = usersEntity.getUserName();
		this.userId = usersEntity.getUserId();
		this.usreEmail = usersEntity.getUserEmail();

	};

	public static ResponseEntity<GetSingInUserResponseDto> success(UsersEntity usersEntity) {

		GetSingInUserResponseDto responseBody = new GetSingInUserResponseDto(usersEntity);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);

	};
	
}
