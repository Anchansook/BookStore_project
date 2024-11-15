package com.study.bookstore.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.study.bookstore.dto.request.review.PostReviewRequestDto;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.books.GetReviewListResponseDto;
import com.study.bookstore.entity.ReviewEntity;
import com.study.bookstore.repository.ReviewRepository;
import com.study.bookstore.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImplement implements BookService {

	private final ReviewRepository reviewRepository;
	
	// 리뷰 작성
	@Override
	public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto, String userId) {

		try {

			boolean isExistUserId = reviewRepository.existsByUserId(userId);
			if (isExistUserId) return ResponseDto.duplicatedUserId();

			ReviewEntity reviewEntity = new ReviewEntity(dto);
			reviewRepository.save(reviewEntity);

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return ResponseDto.success();

	}

	// 리뷰 조회
	@Override
	public ResponseEntity<? super GetReviewListResponseDto> getReviewList(Integer reviewId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getReviewList'");
	}
	
}
