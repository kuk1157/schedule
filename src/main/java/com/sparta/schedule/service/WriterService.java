package com.sparta.schedule.service;

import com.sparta.schedule.dto.WriterRequestDto;
import com.sparta.schedule.dto.WriterResponseDto;
import com.sparta.schedule.entity.Writer;
import com.sparta.schedule.repository.WriterRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class WriterService {
    private final JdbcTemplate jdbcTemplate;
    public WriterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 작성자 명단 조회 - 일정등록 SELECT, 일정 수정 SELECT (레벨 2,3,4 연동)
    public List<WriterResponseDto> getwriters() {
        WriterRepository writerRepository = new WriterRepository(jdbcTemplate);
        return writerRepository.allWriters();
    }

    public WriterResponseDto createWriter(WriterRequestDto writerRequestDto) {
        // RequestDto - Entity
        Writer writer = new Writer(writerRequestDto);

        // ScheduleRepository에서 db관련 처리
        WriterRepository writerRepository = new WriterRepository(jdbcTemplate);
        Writer insertWriter = writerRepository.insert(writer);

        // Entity - ResponseDto
        WriterResponseDto writerResponseDto = new WriterResponseDto(writer);
        return writerResponseDto;

    }
}
