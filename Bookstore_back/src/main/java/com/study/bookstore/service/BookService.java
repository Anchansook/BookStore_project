package com.study.bookstore.service;

import org.springframework.http.ResponseEntity;

import com.study.bookstore.dto.request.review.PostReviewRequestDto;
import com.study.bookstore.dto.response.ResponseDto;

public interface BookService {

	// 리뷰 작성
	ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId);
	
}
