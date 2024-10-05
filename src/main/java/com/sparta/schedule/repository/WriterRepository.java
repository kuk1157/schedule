package com.sparta.schedule.repository;

import com.sparta.schedule.dto.WriterResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.Writer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class WriterRepository {

    private final JdbcTemplate jdbcTemplate;
    public WriterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 작성자 명단 조회 - 일정등록 SELECT, 일정 수정 SELECT (레벨 2,3,4 연동)
    public List<WriterResponseDto> allWriters() {
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

    // 작성자등록 DB 저장(INSERT) - POST
    public Writer insert(Writer writer) {
        String sql = "INSERT INTO writer (name, email, reg_date) VALUES (?, ?, now()) ";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, writer.getName()); // 이름
            ps.setString(2, writer.getEmail()); // 이메일
            return ps;
        });
        return writer;
    }
}
