package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정등록 DB저장(INSERT) - POST
    public Schedule insert(Schedule schedule) {
        // INSERT 쿼리
        String sql = "INSERT INTO schedule (w_id, memo, pw, pw_check, reg_date, edit_date) VALUES (?, ?, ?, ?, now(), now()) ";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(schedule.getW_id()));
            ps.setString(2, schedule.getMemo());
            ps.setString(3, schedule.getPw());
            ps.setString(4, schedule.getPw_check());
            return ps;
        });
        return schedule;
    }

    // 일정전체조회 DB조회(SELECT) - GET
    public List<ScheduleResponseDto> allSelect() {

        // SELECT 쿼리
        String sql = "SELECT a.id, a.w_id, a.memo, a.w_name, a.pw, a.reg_date, a.edit_date, b.name FROM schedule a LEFT JOIN writer b ON a.w_id = b.id ORDER BY edit_date DESC";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                int w_id = rs.getInt("w_id");
                String memo = rs.getString("memo");
                String w_name = rs.getString("w_name");
                String pw = rs.getString("pw");
                String reg_date = rs.getString("reg_date");
                String edit_date = rs.getString("edit_date");
                String name = rs.getString("name");
                return new ScheduleResponseDto(id, w_id, memo, w_name, pw, reg_date, edit_date, name);
            }
        });
    }

    // 일정검색조회 DB조회(SELECT) - GET
    public List<ScheduleResponseDto> searchFind(String w_id, String memo, String date) {

        // SELECT 쿼리
        String sql = "SELECT *, (SELECT name FROM writer WHERE id = schedule.w_id) as name FROM schedule WHERE id is NOT NULL";

        // 검색 기능 쿼리문 가공(작성자ID, 날짜, 일정내용)
        sql += (w_id == "") ? "" : " AND w_id = "+w_id; // 작성자 검색 삼항연산자
        sql += (memo == "") ? "" : " AND memo LIKE '%"+memo+"%'"; // 메모 검색 삼항연산자
        sql += (date == "") ? "" : " AND DATE_FORMAT(edit_date, '%Y-%m-%d') BETWEEN DATE_SUB(DATE_FORMAT(now(), '%Y-%m-%d'), INTERVAL  "+date+" DAY) AND DATE_FORMAT(now(), '%Y-%m-%d')"; // 날짜 검색
        sql += " ORDER BY edit_date DESC";
        System.out.println(sql); // 쿼리문 출력

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                int w_id = rs.getInt("w_id");
                String memo = rs.getString("memo");
                String w_name = rs.getString("w_name");
                String pw = rs.getString("pw");
                String reg_date = rs.getString("reg_date");
                String edit_date = rs.getString("edit_date");
                String name = rs.getString("name");
                return new ScheduleResponseDto(id, w_id, memo, w_name, pw, reg_date, edit_date, name);
            }
        });
    }

    // 일정 등록한 작성자목록 조회 DB조회(SELECT) - GET
    public List<ScheduleResponseDto> selectWList() {
        // SELECT 쿼리
        String sql = "SELECT a.id, a.w_id, b.name FROM schedule a LEFT JOIN writer b ON a.w_id = b.id WHERE a.id IS NOT NULL GROUP BY a.w_id ORDER BY a.w_id ASC";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                int w_id = rs.getInt("w_id");
                String name = rs.getString("name");
                return new ScheduleResponseDto(id, w_id, name);
            }
        });
    }

    // 일정 수정 (UPDATE) - PUT
    public void update(int id, ScheduleRequestDto scheduleRequestDto) {
        // UPDATE 쿼리
        String sql = "UPDATE schedule SET w_id = ?, memo = ?, edit_date = now() WHERE id = ?";
        jdbcTemplate.update(sql, scheduleRequestDto.getW_id(), scheduleRequestDto.getMemo(), id);
    }

    // 일정 삭제 (DELETE) - DELETE
    public void delete(int id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
