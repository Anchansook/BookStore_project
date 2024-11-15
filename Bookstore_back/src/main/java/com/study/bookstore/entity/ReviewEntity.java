package com.study.bookstore.entity;

import com.study.bookstore.dto.request.review.PostReviewRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* 리뷰 테이블 엔터티 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="reviews")
@Table(name="reviews")
public class ReviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	private Integer bookId;
	private String userId;
	private Integer reviewRating;
	private String reviewContent;
	private String reviewDate;
	
	// 리뷰 작성 시 데이터를 삽입하기 위한 생성자
	public ReviewEntity(PostReviewRequestDto dto) {
		this.bookId = dto.getBookId();
		this.userId = dto.getUserId();
		this.reviewRating = dto.getReviewRating();
		this.reviewContent = dto.getReviewContent();
		this.reviewDate = dto.getReviewDate();
	};

	// 리뷰 수정을 위한 생성자

}
