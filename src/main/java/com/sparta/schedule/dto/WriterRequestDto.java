package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class WriterRequestDto {
    // 작성자 테이블
    private int id; // 작성자 아이디
    private String name; // 작성자 이름
    private String email; // 작성자 이메일
}
