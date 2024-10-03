package com.sparta.schedule.controller;

import com.sparta.schedule.dto.WriterResponseDto;
import com.sparta.schedule.service.ScheduleService;
import com.sparta.schedule.service.WriterService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class WriterController {
    private final JdbcTemplate jdbcTemplate;
    public WriterController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 작성자 명단 조회 - 일정등록 SELECT, 일정 수정 SELECT (레벨 2,3,4 연동)
    @GetMapping("/writer")
    public List<WriterResponseDto> getwriters() {
        WriterService writerService = new WriterService(jdbcTemplate);
        return writerService.getwriters();

    }
}
