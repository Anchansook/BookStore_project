package com.study.bookstore.repository.resultSet;

//* 리뷰를 불러오기 위한 resultSet */

public interface GetReviewResultSet {

	String getBookName();
	Integer getReviewRating();
	String getReviewContent();
	String getReviewDate();
	
}
