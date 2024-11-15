package com.study.bookstore.dto.response.books;

import com.study.bookstore.dto.response.ResponseCode;
import com.study.bookstore.dto.response.ResponseDto;
import com.study.bookstore.dto.response.ResponseMessage;
import com.study.bookstore.repository.resultSet.GetReviewResultSet;

import lombok.Getter;

//* 리뷰 내용 불러오는 응답 dto */

@Getter
public class GetReviewResponseDto extends ResponseDto {

	private String bookName;
	private Integer reviewRating;
	private String reviewContent;
	private String reviewDate;
	// 가져올 컬럼들 타입 달라서 resultSet 작성함

	private GetReviewResponseDto(GetReviewResultSet resultSet) {

		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.bookName = resultSet.getBookName();
		this.reviewRating = resultSet.getReviewRating();
		this.reviewContent = resultSet.getReviewContent();
		this.reviewDate = resultSet.getReviewDate();

	};

	public static 
	
}
