package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto { // 데이터 한줄
    private int id; // 일정 고유아이디
    private int w_id; // 작성자 고유아이디
    private String w_name; // 작성자 이름
    private String memo; // 내용
    private String pw; // 비밀번호
    private String pw_check; // 비밀번호확인
    private String reg_date; // 작성시간
    private String edit_date;

    private String name; // 작성자 이름 서브쿼리
}
