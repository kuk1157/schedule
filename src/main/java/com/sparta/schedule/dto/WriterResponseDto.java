package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Writer;
import lombok.Getter;

@Getter
public class WriterResponseDto {
    // 작성자 테이블
    private int id; // 작성자 아이디
    private String name; // 작성자 이름
    private String email; // 작성자 이메일

    public WriterResponseDto(Writer writer) {
        this.id = writer.getId();
        this.name = writer.getName();
        this.email = writer.getEmail();
    }

    public WriterResponseDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
