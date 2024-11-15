package com.study.bookstore.dto.response.books;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.study.bookstore.common.object.Review;
import com.study.bookstore.dto.response.ResponseCode;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.ResponseMessage;
import com.study.bookstore.entity.ReviewEntity;

import lombok.Getter;

//* 리뷰 조회 응답 dto */

@Getter
public class GetReviewListResponseDto extends ResponseDto {

	// 리뷰 리스트 객체 작성했음
	private List<Review> reviews;

	private GetReviewListResponseDto(List<ReviewEntity> reviewEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.reviews = Review.getList(reviewEntities);
	};

	public static ResponseEntity<GetReviewListResponseDto> success(List<ReviewEntity> reviewEntities) {
		GetReviewListResponseDto responseBody = new GetReviewListResponseDto(reviewEntities);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	};
}
