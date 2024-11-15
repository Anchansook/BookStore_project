package com.study.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.bookstore.entity.ReviewEntity;

//* 리뷰 엔터티의 리뷰 리포지토리 */

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

	// 사용자가 이미 작성한 리뷰가 있는 지 확인
	boolean existsByUserId(String userId);
	
}
