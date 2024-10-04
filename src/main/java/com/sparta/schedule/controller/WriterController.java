package com.sparta.schedule.controller;

import com.sparta.schedule.dto.WriterRequestDto;
import com.sparta.schedule.dto.WriterResponseDto;
import com.sparta.schedule.service.WriterService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class WriterController {
    private final JdbcTemplate jdbcTemplate;
    public WriterController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 작성자 등록 - 작성자 등록 페이지에서 api 전송
    @PostMapping("/writer")
    public WriterResponseDto CreateWriter(@RequestBody WriterRequestDto writerRequestDto) {
        WriterService writerService = new WriterService(jdbcTemplate);
        return writerService.createWriter(writerRequestDto);
    }

    // 작성자 명단 조회 - 일정등록 SELECT, 일정 수정 SELECT (레벨 2,3,4 연동)
    @GetMapping("/writer")
    public List<WriterResponseDto> getwriters() {
        WriterService writerService = new WriterService(jdbcTemplate);
        return writerService.getwriters();

    }
}
