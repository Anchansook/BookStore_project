<h1 style='background-color: rgba(55, 55, 55, 0.4); text-align: center'>API 설계(명세)서 </h1>

해당 API 명세서는 'Bookstore'의 REST API를 명세하고 있습니다.  

- Domain : http://localhost:4000    

***
  
<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Users 모듈</h2>

Bookstore 서비스의 고객(사용자)과 관련된 REST API 모듈입니다.  
고객 정보 확인, 고객 정보 수정, 고객 탈퇴 등의 API가 포함되어 있습니다.
Users 모듈은 모두 인증이 필요합니다.  
  
- url : /api/v1/users  

***

#### - 로그인한 사용자 정보 확인  
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하고, URL에 고객 아이디를 포함하여 요청하고, 성공적으로 이루어지면 성공에 대한 응답으로 토큰에 해당하는 고객의 아이디와 이름, 전화번호를 응답 받습니다.  
만약 존재하지 않는 아이디일 경우 존재하지 않는 아이디에 대한 응답을 받습니다.  
네크워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : GET  
- end point : /{userId}  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/v1/users/qwer1234" \
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
| userId | String | 고객 아이디 | O |
| userName | String | 고객 이름 | O |
| userEmail | String | 고객 이메일 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "userId": "qwer1234",
  "userName": "홍길동",
  "userEmail": "qwer1234@naver.com"
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

**응답 : 실패 (전화번호 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "TAF",
  "message": "Tel number authentication failed."
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

#### - 회원 정보 수정  
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함해야 합니다.  
이름, 이메일을 입력하여 요청하고 회원 수정이 성공적으로 이루어지면 성공에 대한 응답을 받습니다.  
만약 존재하지 않는 회원일 경우 존재하지 않는 고객에 대한 응답을 받습니다.  
네트워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : PATCH  
- end point : /{userId}  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userName | String | 사용자의 이름 | O |
| userId | String | 사용자의 아이디 | O |
| userPassword | String | 사용자의 비밀번호 (8~13자의 영문 + 숫자) | O |
| userEmail | String | 사용자의 전화번호 (11자의 숫자) | O |

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/v1/users/qwer1234" \
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

**응답 : 실패 (존재하지 않는 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NI",
  "message": "No exist user id."
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

#### - 회원 탈퇴 
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함해야 합니다.  
URL에 유저 아이디를 포함하여 요청하고 회원 삭제가 성공적으로 이루어지면 성공에 대한 응답을 받습니다.  
만약 존재하지 않는 회원일 경우 존재하지 않는 고객에 대한 응답을 받습니다.  
네트워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : DELETE  
- end point : /delete-me

##### Request

###### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/v1/users/delete-me" \
```

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |


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

**응답 : 실패 (존재하지 않는 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NI",
  "message": "No exist user id."
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