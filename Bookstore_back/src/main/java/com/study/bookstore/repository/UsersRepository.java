package com.study.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.bookstore.entity.UsersEntity;

//* users 리포지토리 */

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {

	// 아이디 중복 확인 (+ 회원가입)
	boolean existsByUserId(String userId);
	
	// 이메일 중복 확인 (+ 회원가입)
	boolean existsByUserEmail(String userEmail);

	//* 로그인 */
	// 아이디 존재 여부 확인
	UsersEntity findByUserId(String userId);

}
