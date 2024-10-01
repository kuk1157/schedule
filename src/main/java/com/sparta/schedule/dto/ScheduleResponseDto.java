package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private int id; // 일정 고유아이디
    private int w_id; // 작성자 고유아이디
    private String w_name; // 작성자 이름
    private String memo; // 내용
    private String pw; // 비밀번호
    private String pw_check; // 비밀번호확인
    private String reg_date; // 등록시간
    private String edit_date;

    private String name; // 작성자 이름 서브쿼리

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.w_id = schedule.getW_id();
        this.w_name = schedule.getW_name();
        this.memo = schedule.getMemo();
        this.pw = schedule.getPw();
        this.pw_check = schedule.getPw_check();
        this.reg_date = schedule.getReg_date();
        this.edit_date = schedule.getEdit_date();
        this.name = schedule.getName();
    }

    public ScheduleResponseDto(int id, int w_id, String memo, String w_name, String pw, String reg_date, String edit_date, String name) {
        this.id = id;
        this.w_id = w_id;
        this.w_name = w_name;
        this.memo = memo;
        this.pw = pw;
        this.reg_date = reg_date;
        this.edit_date = edit_date;
        this.name = name;
    }

    public ScheduleResponseDto(int id, int w_id, String name) {
        this.id = id;
        this.w_id = w_id;
        this.name = name;
    }

    public ScheduleResponseDto() {

    }
//    private String s_pw_check; // 비밀번호확인
}
