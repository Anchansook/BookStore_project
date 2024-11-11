package com.study.bookstore.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.study.bookstore.dto.request.auth.IdCheckRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.provider.JwtProvider;
import com.study.bookstore.repository.UsersRepository;
import com.study.bookstore.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

	private final JwtProvider jwtProvider;

	private final UsersRepository usersRepository;
	
	// 아이디 중복 확인
	@Override
	public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
		
		String userId = dto.getUserId();

		try {

			boolean isExistedId = usersRepository.existsByUserId(userId);
			if (!isExistedId) return ResponseDto.duplicatedUserId();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return ResponseDto.success();

	}
	
}
