package com.study.bookstore.dto.response;

//* 공통 응답 코드 */

public interface ResponseCode {

	String SUCCESS = "SU";

	String VALIDATION_FAIL = "VF";

	String DUPLICATED_USER_ID = "DI";
	String DUPLICATED_USER_EMAIL = "DE";

	String NO_EXIST_USER_ID = "NI";
	String NO_EXIST_CATEGORY = "NC";
	String NO_EXIST_DISCOUNT = "ND";
	String NO_EXIST_ORDER_CODE = "NO";
	String NO_EXIST_BOOK = "NB";
	
	String SIGN_IN_FAIL = "SF";

	String TOKEN_CREATE_FAIL = "TCF";

	String DATABASE_ERROR = "DBE";

	String AUTHENTICATION_FAIL = "AF";

	String NO_PERMISSION = "NP";
	
}
