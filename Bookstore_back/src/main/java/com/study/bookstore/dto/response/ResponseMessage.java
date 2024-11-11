package com.study.bookstore.dto.response;

//* 공통 응답 메시지 */

public interface ResponseMessage {

	String SUCCESS = "Success.";

	String VALIDATION_FAIL = "Validation failed.";

	String DUPLICATED_USER_ID = "Duplicated user id.";
	String DUPLICATED_USER_EMAIL = "Duplicated user email.";

	String NO_EXIST_USER_ID = "No exist user id.";
	String NO_EXIST_CATEGORY = "No exist category by book.";
	String NO_EXIST_DISCOUNT = "No exist discount by book.";
	String NO_EXIST_ORDER_CODE = "No exist order code.";

	String SIGN_IN_FAIL = "Sign in failed.";

	String TOKEN_CREATE_FAIL = "Token creation failed.";

	String DATABASE_ERROR = "Database error.";

	String AUTHENTICATION_FAIL = "Authentication fail.";

	String NO_PERMISSION = "No permission.";
	
}
