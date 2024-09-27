package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto { // 데이터 한줄
    // [ 레벨 4때 사용 ]
//    private int w_idx; // 작성자 고유번호
//    private String w_name; // 작성자 이름
    private String memo; // 내용(컬럼)
    private String pw; // 비밀번호(컬럼)
//    private String s_pw_check; // 비밀번호확인
}
