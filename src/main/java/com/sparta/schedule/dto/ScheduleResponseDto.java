package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    // [ 레벨 4때 사용 ]
//    private int w_idx; // 작성자 고유번호
//    private String w_name; // 작성자 이름
    private String memo; // 내용
    private String pw; // 비밀번호

    public ScheduleResponseDto(Schedule schedule) {
        this.memo = schedule.getMemo();
        this.pw = schedule.getPw();
    }
//    private String s_pw_check; // 비밀번호확인
}
