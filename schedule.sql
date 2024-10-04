# [테이블 CREATE문]

# 1. writer 테이블 (작성자테이블)

CREATE TABLE writer(  
id int(7) not null auto_increment comment '작성자 고유번호',
name varchar(45) comment '이름',
email varchar(45) comment '이메일',
reg_date datetime comment '작성일\nㄴ 작성되는 동시 자동',
edit_date datetime comment '수정일',
PRIMARY KEY(id)
);


# 2. schedule 테이블 (일정테이블)

CREATE TABLE schedule(  
id int(7) not null auto_increment comment '일정 고유번호',
w_id int(7) comment '작성자 고유번호 \nㄴ 작성자 테이블과 연동',
memo varchar(150) not null comment '일정 내용',
w_name varchar(50) comment '작성자 이름',
pw longtext not null comment '비밀번호',
pw_check longtext comment '비밀번호 확인',
reg_date datetime not null comment '작성일\nㄴ 작성되는 동시 자동',
edit_date datetime not null comment '수정일\nㄴ 작성일과 동시에 저장\nㄴ 추후에 수정할때 수정하는 시점으로 UPDATE',
PRIMARY KEY(id)
);