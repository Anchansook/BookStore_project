package com.study.bookstore.entity;

import com.study.bookstore.dto.request.auth.SignUpRequestDto;
import com.study.bookstore.dto.request.users.PatchUserRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* users 테이블 엔터티 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
@Table(name="users")
public class UsersEntity {

	@Id
	private String userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	
	// 회원가입 시 데이터를 삽입하기 위한 생성자
	public UsersEntity(SignUpRequestDto dto) {
		this.userName = dto.getUserName();
		this.userId = dto.getUserId();
		this.userEmail = dto.getUserEmail();
		this.userPassword = dto.getUserPassword();
	}

	// 회원 수정을 위한 생성자
	public UsersEntity(PatchUserRequestDto dto) {
		this.userName = dto.getUserName();
		this.userEmail = dto.getUserEmail();
	};
}
