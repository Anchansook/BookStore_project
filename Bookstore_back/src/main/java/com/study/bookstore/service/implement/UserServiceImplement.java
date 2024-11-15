package com.study.bookstore.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.study.bookstore.dto.request.users.PatchUserRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.users.GetSingInUserResponseDto;
import com.study.bookstore.entity.UsersEntity;
import com.study.bookstore.repository.UsersRepository;
import com.study.bookstore.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

	private final UsersRepository usersRepository;

	// 로그인 유저 정보 확인
	@Override
	public ResponseEntity<? super GetSingInUserResponseDto> getSignIn(String userId) {

		UsersEntity usersEntity = null;
		
		try {

			usersEntity = usersRepository.findByUserId(userId);
			if (usersEntity == null) return ResponseDto.noExistUserId();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return GetSingInUserResponseDto.success(usersEntity);
		
	}

	// 회원 수정
	@Override
	public ResponseEntity<ResponseDto> patchUser(PatchUserRequestDto dto, String userId) {

		try {

			String userName = dto.getUserName();
			String userEmail = dto.getUserEmail();

			UsersEntity usersEntity = usersRepository.findByUserId(userId);
			if (usersEntity == null) return ResponseDto.noExistUserId();

			usersEntity.setUserName(userName);
			usersEntity.setUserEmail(userEmail);

			usersRepository.save(usersEntity);

		} catch(Exception exception) {
			exception.printStackTrace();
			ResponseDto.databaseError();
		};

		return ResponseDto.success();
		
	}
	
}
