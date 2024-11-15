package com.study.bookstore.service;

import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.request.review.PostReviewRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.books.GetReviewListResponseDto;

public interface BookService {

	// 리뷰 작성
	ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId);
	// 리뷰 조회
	ResponseEntity<? super GetReviewListResponseDto> getReviewList(Integer reviewId);
}
