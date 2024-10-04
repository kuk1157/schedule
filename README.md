# [ Spring 개인과제_1 - 일정관리 게시판(앱)  ]

개인과제 레벨 2,3,4 : https://kuk1938.tistory.com/154

개인과제 레벨 5 : https://kuk1938.tistory.com/156

트러블 슈팅 : https://kuk1938.tistory.com/155

#

### 레벨1 - 일정관리 설계서) 
![image](https://github.com/user-attachments/assets/59ec9222-d2d6-435e-890f-38683e2ae160)

ㄴ ERD

#
![image](https://github.com/user-attachments/assets/d4dffdbe-f061-4d01-bc38-d18768c6586c)

ㄴ 요구사항 및 API명세서

#
![image](https://github.com/user-attachments/assets/7f7932be-d8ce-4499-a55e-899eff47fa87)

ㄴ API명세서 상세

https://moonddev-9794.postman.co/workspace/moonddev-Workspace~c3ed51a4-6c51-4a48-b8d6-b6e3245bdf3a/collection/38606783-c8a0bb09-78a2-4592-904c-2b695ed39dbb?action=share&creator=38606783

ㄴ API명세서 링크



# [일정관리 전체 흐름]

: 레벨별로 큰 흐름을 정리했고, 페이징관련 로직은 paging객체없이 구현을 하였기 때문에 설명이 좀 깁니다.


## [레벨 2]


#### 일정등록)

- 작성자, 내용, 비밀번호, 비밀번호확인을 입력받도록
- 작성자는 SELECT형태로 받는다 (작성자테이블을 생성하고 해당 목록을 추출해서 아이디를 DB에 저장하는형태 - 레벨4파트)
- 비밀번호와 비밀번호확인이 일치할 경우에만 등록이 되도록한다. (스크립트 기능 보완할 예정)
- 일정아이디는 기본키와 오토인크리먼트설정으로 순서대로 등록되도록
- 등록일과 수정일은 MySQl NOW()를 활용해서 현재시간이 등록되도록

#### 일정전체조회)

- 상단에 작성자, 수정일 하단메인에 내용,비밀번호입력, 수정,삭제버튼 형태로 리스트 구성
- 페이지 로딩되자마자 SELECT쿼리로 일정 전체 데이터 조회
- LEFT JOIN으로 작성자테이블과 연동해서 작성자명을 호출(레벨4파트)
- html을 append해서 목록 뿌려주기

#### 일정선택조회)

- 목록에서 상세보기 버튼으로 이동
- 상단에 일정ID, 일정 수정일 출력
- 하단에 작성자, 일정내용 수정 가능
- 비밀번호 입력하여 일치할 경우에만 수정
- 이전 버튼 누르면 일정목록(메인페이지로 이동)


## [레벨 3]


#### 일정수정)

- 일정내용과 작성자 수정되도록(작성자는 select로 등록과 마찬가지로 작성자아이디가 수정되는형태)
- 등록된 비밀번호와 일치해야 수정할 수 있음.
- 수정할때 수정일이 now()로 수정하는 현재시간으로 업데이트 시킴.
- 수정아이콘을 클릭했을때 수정할 수 있는 내용과 작성자의 입력란이 노출됨.
- 최종 [수정] 버튼 클릭시 input활성화 및 비밀번호 입력하였을 경우에만 수정 되도록

#### 일정삭제)

- 선택한 일정 삭제
- 등록된 비밀번호와 일치해야 삭제할 수 있음.



## [레벨 4]


#### 작성자연동)

- 일정등록시 작성자테이블에서 가져온 작성자아이디를 등록시켜서 연동함.
- 해당 작성자id를 통해서 전체목록조회에선 LEFT JOIN, 검색조회땐 서브쿼리활용해서 작성자이름을 땡겨옴
- 수정아이콘을 클릭했을때 수정할 수 있는 내용과 작성자의 입력란이 노출됨.
- 최종 [수정] 버튼 클릭시 input활성화 및 비밀번호 입력하였을 경우에만 수정 되도록


#### 작성자등록)
- 메인 페이지에서 작성자등록 버튼 클릭시 작성자등록 페이지이동
- 이름 이메일만 입력받아서 API전송
- DB에 저장되고, 일정등록 및 수정 작성자 SELECT박스에 반영


#### 일정 검색조회)

- 날짜, 작성자, 일정내용 3개 검색항목을 만들었음.
- 날짜는 일수를 활용해서 1일,일주일,1개월,3개월로 범위 검색
- 작성자는 일정테이블에서 등록된 작성자목록만 select박스로 쿼리를 따로 만들어서 조회해서 등록된 작성자목록만 검색할 수 있음
- 일정내용은 like을 활용해서 검색가능하도록 했음.
- 각 항목마다 값이 아예 없을 경우엔 쿼리문에 추가로 붙지않도록 설정해두어서 1개 2개 3개 각각 모든 경우의 수로 검색이 가능하도록 설정했음.
- 특정 조건에서 아예 데이터가 조회되지 않을 경우에는 데이터가 "조회되지 않습니다"를 노출 시켜주고 있음.
- 우측 상단에 검색버튼으로 최종 검색이 가능하며, 초기화 버튼을 누르면 초기에 전체 일정목록을 불러오고 모든 검색값도 초기화시킴.



## [레벨 5]

### 페이징 전체 로직
![image](https://github.com/user-attachments/assets/59b9c9be-e717-438a-936b-faeeb5c49f80)

![image](https://github.com/user-attachments/assets/a17d5d54-8bf0-449e-9661-adc73712607c)

### ↓↓↓ 아래 상세설명 참고
### ↓↓↓ 아래 상세설명 참고


### [초기에 생각한 로직 흐름도]

#### 로딩시 이벤트실행)

- 페이징 숫자 클릭시 schedule테이블 count구해오기
- default 게시글 수, count를 활용해서 count가 클 경우 반복문으로 페이징 생성(append시키기)
- 각 버튼을 누를때마다 디폴트 갯수만큼 더해준다 / 디폴트 개수는 계속 게시글 수만큼 구해주는 역할을 한다.
- 위 공식으로 쿼리문에 limit 몇번째부터(디폴트 개수+디폴트개수), 데이터개수(디폴트 개수)를 구해온다

[예시]

[디폴트 2]

0,2

2,4

4,6

6,8

8,10

#### 실제 일정테이블 데이터 조회)

- 위 형태로 버튼누를때마다 보이는 데이터가 바뀌도록 설정한다.
- default > count일 경우는 그냥 1번만 보이고 이벤트 실행도 안되고 그냥 보이기만 하는 영역으로 둔다.

#
### [실제 페이징 로직 흐름도]

#### 실제 로직흐름)

1) schedule테이블 데이터 COUNT() 집계함수로 구해오기
- 페이지 로딩시 바로 구해오기

2) 전역변수로 선언해둔 [기본 게시글 수] >= [schedule테이블 데이터개수] 조건문생성
- 기본게시글수가 크거나 같으면 기본페이지번호 1만 노출
- schedule테이블 데이터개수가 크면 페이징 전체 로직 실행

3) [데이터 개수]기준으로 반복문 실행
- 기본 게시글수의 배수를 구하기
- 배수보다 1이클때 페이징번호가 1개씩 늘어나야함 (변수로 값을 담아줌 배수+1)
- if( i == (배수+1) ) 일때 페이징번호 변수를 증감시키고 페이징영역 append로직 시작
- 페이지번호 1일때 0,기본게시글수를 담아서 html append 및 일정테이블전제조회 api 실행(LIMIT 0,[기본게시글수] )
- 1보다 클 경우엔 조건에 맞게 html append append 및 일정테이블전제조회 api 실행(LIMIT [0+기본게시글수], [기본게시글수])

4) 최종 페이징 번호 생성
- 반복문 밖에서 html append 및 일정테이블전제조회 api 실행 최종 ( LIMIT [증감한 기준 수] [기본게시글 수] )
  
※ 페이징번호는 최종값에 +1해서 마지막 페이징번호를 넣어준다.

#### 기타설명)

★ LIMIT 쿼리에서 순간 착각을 했는데 페이징번호 기준으로 LIMIT으로 다음 데이터를 보여줄때 기본게시글수는 고정이 되어있어야한다.

[초기 흐름]

기본게시글 수 : 3

LIMIT 0,3

LIMIT 3,6

LIMIT 6,9

[실제 흐름]

기본게시글 수 : 3

LIMIT 0,3

LIMIT 3,3

LIMIT 6,3

★ 실제흐름과 같은형태로 LIMIT의 앞 번호만 바뀌어야한다.

★ LIMIT [몇번째부터] [보여줄 개수]


