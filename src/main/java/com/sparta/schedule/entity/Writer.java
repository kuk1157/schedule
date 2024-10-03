package com.sparta.schedule.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 작성자 테이블 전체
public class Writer {
    private int id; // 작성자 아이디
    private String name; // 작성자 이름
    private String email; // 작성자 이메일
}
