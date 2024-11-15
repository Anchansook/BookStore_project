package com.study.bookstore.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* 리뷰 작성을 하기 위한 요청 dto */

@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequestDto {

	@NotNull
	private Integer bookId;
	@NotBlank
	private String userId;
	@NotNull
	private Integer reviewRating;
	private String reviewContent;
	@NotBlank
	private String reviewDate;
	
}
