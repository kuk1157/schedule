package com.sparta.schedule.repository;

import com.sparta.schedule.dto.WriterResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WriterRepository {

    private final JdbcTemplate jdbcTemplate;
    public WriterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 작성자 명단 조회 - 일정등록 SELECT, 일정 수정 SELECT (레벨 2,3,4 연동)
    public List<WriterResponseDto> allWriters() {

        // SELECT 쿼리 - SELECT 박스에 필요한 작성자 ID, 작성자 이름만 조회
        String sql = "SELECT id,name FROM writer";
        return jdbcTemplate.query(sql, new RowMapper<WriterResponseDto>() {
            @Override
            public WriterResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id"); // 작성자 ID
                String name = rs.getString("name"); // 작성자 이름
                return new WriterResponseDto(id, name);
            }
        });
    }
}
