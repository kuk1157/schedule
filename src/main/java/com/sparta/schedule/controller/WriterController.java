package com.sparta.schedule.controller;

import com.sparta.schedule.dto.WriterResponseDto;
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

    @GetMapping("/writer")
    public List<WriterResponseDto> getwriters() {
        String sql = "SELECT id,name FROM writer";

        return jdbcTemplate.query(sql, new RowMapper<WriterResponseDto>() {
            @Override
            public WriterResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                return new WriterResponseDto(id, name);
            }
        });
    }

}
