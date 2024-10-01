package com.sparta.schedule.entity;


import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {

    private int id; // 일정 고유아이디
    private int w_id; // 작성자 고유아이디
    private String w_name; // 작성자 이름
    private String memo; // 내용
    private String pw; // 비밀번호
    private String pw_check; // 비밀번호확인
    private String reg_date; //
    private String edit_date;

    private String name; // 작성자 이름 서브쿼리


    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.id = scheduleRequestDto.getId();
        this.w_id = scheduleRequestDto.getW_id();
        this.w_name = scheduleRequestDto.getW_name();
        this.memo = scheduleRequestDto.getMemo();
        this.pw = scheduleRequestDto.getPw();
        this.pw_check = scheduleRequestDto.getPw_check();
        this.reg_date = scheduleRequestDto.getReg_date();
        this.edit_date = scheduleRequestDto.getEdit_date();
        this.name = scheduleRequestDto.getName();
    }
//    private String s_pw_check; // 비밀번호확인

}
