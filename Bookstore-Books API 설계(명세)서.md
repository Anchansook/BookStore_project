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

#### - 카테고리별 조회  
  
##### 설명

클라이언트는 검색 및 카테고리 필터를 선택하여 요청하고 그에 해당되는 응답을 받게 됩니다.   
만약 해당되는 카테고리 및 내용이 없다면 아무런 응답을 받지 않습니다.    
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다. 

- method : GET  
- end point : /categories-filter  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| category-name | String | 책 카테고리 | X |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/v1/books/categories-filter" \
 -d "category-name=미스테리" \
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
  "accessToken": "${ACCESS_TOKEN}",
  "expiration": "32400"
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
만약 해당되는 내용이 없다면 아무런 응답을 받지 않습니다.    
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다. 

- method : GET  
- end point : /discount-filter  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| discount | String | 할인 중인 책 | X |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/v1/books/discount-filter" \
 -d "discount=미스테리" \
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

클라이언트의 구매 패턴을 파악하여 그에 맞는 책을 추천해줍니다.   
만약 파악되는 내용이 없다면 아무런 추천을 하지 않습니다.    
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다. 

- method : POST  
- end point : /recommend-books  

##### Request

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
  "accessToken": "${ACCESS_TOKEN}",
  "expiration": "32400"
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

#### - 리뷰 작성 *** 여기부터 작성해야 함   
  
##### 설명

클라이언트의 구매 패턴을 파악하여 그에 맞는 책을 추천해줍니다.   
만약 파악되는 내용이 없다면 아무런 추천을 하지 않습니다.    
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다. 

- method : POST  
- end point : /recommend-books  

##### Request

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
  "accessToken": "${ACCESS_TOKEN}",
  "expiration": "32400"
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