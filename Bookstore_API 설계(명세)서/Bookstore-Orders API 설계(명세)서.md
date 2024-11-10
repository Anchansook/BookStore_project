h1 style='background-color: rgba(55, 55, 55, 0.4); text-align: center'>API 설계(명세)서 </h1>

해당 API 명세서는 'Bookstore'의 REST API를 명세하고 있습니다.  

- Domain : http://localhost:4000    

***
  
<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Orders 모듈</h2>

Bookstore 서비스의 주문과 관련된 REST API 모듈입니다.  
장바구니 담기, 주문하기, 결제하기 결제 후 자동으로 상태가 자동 업데이트 등의 API가 포함되어 있으며, Orders 모듈은 모두 인증이 필요합니다.
  
- url : /api/v1/orders

***

#### - 장바구니 담기  
  
##### 설명

클라이언트가 선택한 책을 장바구니에 추가합니다.  
요청이 성공하면 장바구니 상태를 반환합니다.  
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : POST  
- end point : /shopping-cart  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| book_name | String | 장바구니에 담을 책 | O |

###### Example

```bash
curl -X POST "http://localhost:4000/api/v1/orders/shopping-cart" \
 -H "Content-Type: application/json" \
 -d '{"book_name": "매일 한 줄씩 읽는 긍정 문장"}'
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
| shoppingCart | String[] | 장바구니에 담긴 책 리스트 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "shoppingCart": [
    {"book_id": "selectedBook1", "title": "책 제목1"},
    {"book_id": "selectedBook2", "title": "책 제목2"}
  ]
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

#### - 주문하기  
  
##### 설명

클라이언트가 장바구니에 담긴 책을 주문합니다.  
요청이 성공하면 주문 성공 상태가 반환됩니다.  
네트워크 에러, 서버 에러, 데이터베이스 에러, 데이터 유효성 실패가 발생할 수 있습니다.

- method : POST  
- end point : /

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| books[] | String[] | 주문할 책 목록 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/books/orders" \
 -H "Content-Type: application/json" \
 -d "books = [
	"book-name": "자존감을 높여보자",
	"book-name": "오늘도 힘을 내자"
 ]" \
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
  "code": "NP",
  "message": "No empty product."
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

#### - 결제 후 상태 자동 업데이트  
  
##### 설명

결제 완료 후 주문 상태를 자동으로 업데이트합니다.  
요청이 성공하면 업데이트된 상태가 반환됩니다.  
네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : PATCH  
- end point : /state-update

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
| orderState | String | 결제 후 상태 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
   "orderState": "Completed"
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