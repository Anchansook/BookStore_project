package com.study.bookstore.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.bookstore.dto.request.auth.EmailCheckRequestDto;
import com.study.bookstore.dto.request.auth.IdCheckRequestDto;
import com.study.bookstore.dto.request.auth.SignInRequestDto;
import com.study.bookstore.dto.request.auth.SignUpRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.entity.UsersEntity;
import com.study.bookstore.provider.JwtProvider;
import com.study.bookstore.repository.UsersRepository;
import com.study.bookstore.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

	private final JwtProvider jwtProvider;

	private final UsersRepository usersRepository;

	// 암호화 방식 주입
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	// 아이디 중복 확인
	@Override
	public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
		
		String userId = dto.getUserId();

		try {

			boolean isExistedId = usersRepository.existsByUserId(userId);
			if (isExistedId) return ResponseDto.duplicatedUserId();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return ResponseDto.success();

	}

	// 이메일 중복 확인
	@Override
	public ResponseEntity<ResponseDto> emailCheck(EmailCheckRequestDto dto) {

		String userEmail = dto.getUserEmail();

		try {

			boolean isExistedEmail = usersRepository.existsByUserEmail(userEmail);
			if (isExistedEmail) return ResponseDto.duplicatedUserEmail();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return ResponseDto.success();
		
	}

	// 회원 가입
	@Override
	public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {

		String userId = dto.getUserId();
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();

		try {

			boolean isExistedId = usersRepository.existsByUserId(userId);
			if (isExistedId) return ResponseDto.duplicatedUserId();

			boolean isExistedEmail  = usersRepository.existsByUserEmail(userEmail);
			if (isExistedEmail) return ResponseDto.duplicatedUserEmail();

			// 확인 후 비밀번호 암호화 후 정보 데이터베이스에 저장
			String encodedPassword = passwordEncoder.encode(userPassword);
			dto.setUserPassword(encodedPassword);

			UsersEntity usersEntity = new UsersEntity(dto);
			usersRepository.save(usersEntity);

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return ResponseDto.success();
		
	}

	// 로그인
	@Override
	public ResponseEntity<ResponseDto> signIn(SignInRequestDto dto) {

		String userId = dto.getUserId();
		String userPassword = dto.getUserPassword();

		UsersEntity usersEntity = null;

		String accessToken = null;
		
		try {

			usersEntity = usersRepository.findByUserId(userId);
			if (usersEntity == null) return ResponseDto.signInFailed();

			// 비밀번호 비교
			String encodedPassword = usersEntity.getUserPassword();
			boolean isMatched = passwordEncoder.matches(userPassword, encodedPassword);
			if (!isMatched) return ResponseDto.signInFailed();

			// 확인 성공 시 토큰 생성
			accessToken = jwtProvider.create(userId);
			if (accessToken == null) return ResponseDto.tokenCreationFailed();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return ResponseDto.success();
		
	}
	
}
