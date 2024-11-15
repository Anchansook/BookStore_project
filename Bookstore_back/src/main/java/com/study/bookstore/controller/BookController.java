package com.study.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.bookstore.dto.request.review.PostReviewRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	// 리뷰 작성
	@PostMapping("/review-write")
	public ResponseEntity<ResponseDto> postReview(
		@RequestBody @Valid PostReviewRequestDto requestBody,
		@AuthenticationPrincipal String userId
	) {
		ResponseEntity<ResponseDto> response = bookService.postReview(requestBody, userId);
		return response;
	};
	
}
