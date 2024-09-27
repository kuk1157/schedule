package com.sparta.schedule.entity;


import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {

    // [ 레벨 4때 사용 ]
//    private int w_idx; // 작성자 고유번호
//    private String w_name; // 작성자 이름
    private String memo; // 내용
    private String pw; // 비밀번호

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.memo = scheduleRequestDto.getMemo();
        this.pw = scheduleRequestDto.getPw();
    }
//    private String s_pw_check; // 비밀번호확인

}
