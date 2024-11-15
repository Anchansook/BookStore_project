h1 style='background-color: rgba(55, 55, 55, 0.4); text-align: center'>API 설계(명세)서 </h1>

해당 API 명세서는 'Bookstore'의 REST API를 명세하고 있습니다.  

- Domain : http://localhost:4000    

***
  
<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Books 모듈</h2>

Bookstore 서비스의 책과 관련된 REST API 모듈입니다.  
리뷰 및 평점과 카테고리 조회 및 추천 등의 API가 포함되어 있습니다.
Books 모듈은 카테고리 조회 및 추천은 인증 없이 요청할 수 있고,  
리뷰 및 평점은 인증을 해야 합니다.
  
- url : /api/v1/books  

***

#### - 리뷰 작성   
  
##### 설명

요청 헤더에 Bearer 인증 토큰을 포함하고 평점, 리뷰내용을 입력하여 요청하고 리뷰 등록이 성공적으로 이루어지면 성공에 대한 응답을 받습니다.  
리뷰 & 평점은 한 사람당 하나만 작성할 수 있습니다.  
네트워크 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : POST  
- end point : /review-write  

##### Request

##### Header
| name | description | required |
|---|:---:|:---:|  
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| reviewRating | 평점 | Float | O |
| reviewContents | 리뷰 내용 | String | X |

###### Example
```bash
curl -v -X POST "http://localhost:4000/api/v1/books/review-write" \
 -h "Authorization=Bearer XXXX",
 -d "reviewRating = 4.4" \
 -d "reviewContents : '인생책이에요 여러분 꼭 읽어보시길' " 
 ```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (존재하지 않는 주문 코드)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NT",
  "message": "No exist ordercode."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 리뷰 불러오기 (전체)   
  
##### 설명

작성한 평점, 리뷰내용을 불러옵니다.  
성공적으로 이루어지면 성공에 대한 응답을 받습니다.  
리뷰는 인증 없이도 볼 수 있습니다.   
네트워크 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : GET  
- end point : /get-review  

##### Request

##### Header
| name | description | required |
|---|:---:|:---:|  
| Authorization | Bearer 토큰 인증 헤더 | X |

###### Example
```bash
curl -v -X GET "http://localhost:4000/api/v1/books/get-review" \
 -h "Authorization=Bearer XXXX"
 ```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| reviewRating | 평점 | Float | O |
| reviewContents | 리뷰 내용 | String | X |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (존재하지 않는 주문 코드)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NT",
  "message": "No exist ordercode."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 리뷰 불러오기 (본인)   
  
##### 설명

작성한 평점, 리뷰내용을 불러옵니다.  
성공적으로 이루어지면 성공에 대한 응답을 받습니다.  
리뷰는 인증 없이도 볼 수 있습니다.   
네트워크 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : GET  
- end point : /get-review-me  

##### Request

##### Header
| name | description | required |
|---|:---:|:---:|  
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example
```bash
curl -v -X GET "http://localhost:4000/api/v1/books/get-review" \
 -h "Authorization=Bearer XXXX"
 ```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| reviewRating | 평점 | Float | O |
| reviewContents | 리뷰 내용 | String | X |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (존재하지 않는 주문 코드)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NT",
  "message": "No exist ordercode."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 리뷰 수정   
  
##### 설명

요청 헤더에 Bearer 인증 토큰을 포함하여 작성한 평점, 리뷰내용을 불러옵니다. 
평점과 내용을 작성하여 요청합니다.   
성공적으로 이루어지면 성공에 대한 응답을 받습니다.  
실패할 경우 실패에 대한 응답을 받습니다.     
데이터 유효성 에러, 네트워크 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : PATCH  
- end point : /review-update  

##### Request

##### Header
| name | description | required |
|---|:---:|:---:|  
| Authorization | Bearer 토큰 인증 헤더 | X |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| reviewRating | 평점 | Float | O |
| reviewContents | 리뷰 내용 | String | X |

###### Example
```bash
curl -v -X POST "http://localhost:4000/api/v1/books/get-review" \
 -h "Authorization=Bearer XXXX",
 -d "reviewRating = 4.4" \
 -d "reviewContents : '인생책이에요 여러분 꼭 읽어보시길' " 
 ```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 실패 (존재하지 않는 주문 코드)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NT",
  "message": "No exist ordercode."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 리뷰 삭제   
  
##### 설명

요청 헤더에 Bearer 인증 토큰을 포함하여 작성한 평점, 리뷰내용을 불러옵니다. 
본인이 작성한 리뷰에 한해 삭제를 요청합니다.  
성공적으로 이루어지면 성공에 대한 응답을 받습니다.  
실패할 경우 실패에 대한 응답을 받습니다.     
네트워크 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : DELETE  
- end point : /delete-review  

##### Request

##### Header
| name | description | required |
|---|:---:|:---:|  
| Authorization | Bearer 토큰 인증 헤더 | X |

###### Example
```bash
curl -v -X POST "http://localhost:4000/api/v1/books/get-review" \
 -h "Authorization=Bearer XXXX"
 ```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (존재하지 않는 주문 코드)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NT",
  "message": "No exist ordercode."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 카테고리별 조회  
  
##### 설명

클라이언트는 검색 및 카테고리 필터를 선택하여 요청하고 그에 해당되는 응답을 받게 됩니다.   
만약 해당되는 카테고리 및 내용이 없다면 아무런 응답을 받지 않습니다.    
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다. 

- method : GET  
- end point : /categories-filter?category-name={categoryName}

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| categoryName | String | 책 카테고리 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/v1/books/categories-filter" \
 -d "categoryName=미스테리" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| Books[] | String[] | 카테고리에 해당되는 책 리스트 | X |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "Books": [
    { "book_id": "123", "title": "미스테리 도서" },
    { "book_id": "456", "title": "스릴러 도서" }
  ]
}
```

**응답 : 실패 (해당되는 카테고리가 없는 책)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "BC",
  "message": "Books without a corresponding category."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 할인 책 조회  
  
##### 설명

클라이언트는 할인 필터를 선택하여 요청하고 그에 해당되는 응답을 받게 됩니다.   
만약 해당되는 내용이 없다면 아무런 응답을 하지 않습니다.    
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다. 

- method : GET  
- end point : /discount-filter?discount-book={discountBook}  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| discount | String | 할인 중인 책 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/v1/books/discount-filter" \
 -d "discountBook=" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| Books[] | String[] | 할인 중인 책 리스트 | X |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "accessToken": "${ACCESS_TOKEN}",
  "expiration": "32400"
}
```

**응답 : 실패 (존재하지 않는 할인 책)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "ND",
  "message": "No exist discounting books."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 구매 패턴을 반영한 추천 기능   
  
##### 설명

클라이언트의 사용자 아이디와 주문 번호를 활용하여 구매 이력을 추천 알고리즘으로 파악하여 그에 맞는 책을 추천해줍니다.   
만약 파악되는 내용이 없다면 아무런 추천을 하지 않습니다.    
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다. 

- method : POST  
- end point : /recommend-books  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 구매이력을 파악할 사용자의 아이디 | O |
| orderId | Int | 구매이력을 파악할 사용자의 주문 코드 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/v1/books/discount-filter" \
 -d "userId"= "qwer1234" \
 -d "orderId"= "1234" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| Books[] | String[] | 추천하는 책 리스트 | X |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "recommendedBooks": [
    {"book_id": "recommendedBook1", "title": "추천 책 제목1"},
    {"book_id": "recommendedBook2", "title": "추천 책 제목2"}
  ]
}
```

**응답 : 실패 (존재하지 않는 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NI",
  "message": "No exist user id."
}
```

**응답 : 실패 (존재하지 않는 주문 코드)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NO",
  "message": "No exist order_id."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```