package com.study.bookstore.common.object;

import java.util.ArrayList;
import java.util.List;

import com.study.bookstore.entity.ReviewEntity;

import lombok.Getter;

//* 리뷰 리스트 객체 */

@Getter
public class Review {

	private Integer reviewId;
	private Integer bookId;
	private String userId;
	private Integer reviewRating;
	private String reviewContent;
	private String reviewDate;

	private Review(ReviewEntity reviewEntity) {
		this.reviewId = reviewEntity.getReviewId();
		this.bookId = reviewEntity.getBookId();
		this.userId = reviewEntity.getUserId();
		this.reviewRating = reviewEntity.getReviewRating();
		this.reviewContent = reviewEntity.getReviewContent();
		this.reviewDate = reviewEntity.getReviewDate();
	};

	public static List<Review> getList(List<ReviewEntity> reviewEntities) {

		List<Review> reviews = new ArrayList<>();

		for (ReviewEntity reviewEntity: reviewEntities) {
			Review review = new Review(reviewEntity);
			reviews.add(review);
		};

		return reviews;
		
	};
	
}
