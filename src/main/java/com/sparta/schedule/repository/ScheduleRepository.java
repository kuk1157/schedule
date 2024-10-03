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
            ps.setString(1, String.valueOf(schedule.getW_id())); // 작성자 ID
            ps.setString(2, schedule.getMemo()); // 일정 내용
            ps.setString(3, schedule.getPw()); // 일정 비밀번호
            ps.setString(4, schedule.getPw_check()); // 일정 비밀번호 확인
            return ps;
        });
        return schedule;
    }

    // 일정전체조회 DB 조회(SELECT) - GET
    // cnt : 일정 게시글 기본 개수 - getSchedules(함수명)
    // id : 일정 상세보기 일정 ID - getSchedules(함수명)
    // limit1 : 페이징 limit 첫번째 값 - getLimit(함수명)
    // limit2 : 페이징 limit 두번째 값 - getLimit(함수명)
    public List<ScheduleResponseDto> allSelect(String cnt, String id, String limit1, String limit2) {
        // SELECT 쿼리
        String sql = "SELECT a.id, a.w_id, a.memo, a.w_name, a.pw, a.reg_date, a.edit_date, b.name FROM schedule a LEFT JOIN writer b ON a.w_id = b.id";
        sql += (id == null) ? "" : " WHERE a.id = "+id; // 일정 상세보기
        sql += " ORDER BY edit_date DESC"; // 레벨 2 수정일 내림차순 정렬
        // [이중 삼항연산자 사용]
        // ID(상세보기) 값이 있을 경우엔 LIMIT 삭제
        // ID(상세보기) 값이 있을 없을 경우엔 기본 LIMIT 및 페이징 LIMIT 진행
        sql +=  (id != null) ? "" : (limit1 == null) ? " LIMIT 0,"+cnt : " LIMIT "+limit1+","+limit2;
        System.out.println("[일정 목록 및 상세보기 쿼리 확인]");
        System.out.println(sql);


        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id"); // 일정 ID
                int w_id = rs.getInt("w_id"); // 작성자 ID
                String memo = rs.getString("memo"); // 일정 내용
                String w_name = rs.getString("w_name"); // 작성자 이름
                String pw = rs.getString("pw"); // 일정 비밀번호
                String reg_date = rs.getString("reg_date"); // 일정 작성일
                String edit_date = rs.getString("edit_date"); // 일정 수정일
                String name = rs.getString("name"); // 작성자이름
                return new ScheduleResponseDto(id, w_id, memo, w_name, pw, reg_date, edit_date, name);
            }
        });
    }

    // 일정검색조회 DB 조회(SELECT) - GET
    // cnt : 일정 게시글 기본 개수 - searchGet(함수명)
    // limit1 : 페이징 limit 첫번째 값 - getLimitSearch(함수명)
    // limit2 : 페이징 limit 두번째 값 - getLimitSearch(함수명)
    // w_id : 작성자 ID(select) - searchGet(함수명)
    // memo : 일정 내용(input) - searchGet(함수명)
    // date : 기간검색(select) - searchGet(함수명)
    public List<ScheduleResponseDto> searchFind(String count, String limit1, String limit2, String w_id, String memo, String date) {
        String cnt = "";
        cnt += (w_id == "") ? "" : " AND w_id = "+w_id; // 작성자 검색 AND 쿼리
        cnt += (memo == "") ? "" : " AND memo LIKE '%"+memo+"%'"; // 메모 검색 AND 쿼리
        // 날짜 일수 검색
        cnt += (date == "") ? "" : " AND DATE_FORMAT(edit_date, '%Y-%m-%d') BETWEEN DATE_SUB(DATE_FORMAT(now(), '%Y-%m-%d'), INTERVAL  "+date+" DAY) AND DATE_FORMAT(now(), '%Y-%m-%d')"; // 날짜 검색

        // SELECT 쿼리
        String sql = "SELECT *, (SELECT name FROM writer WHERE id = schedule.w_id) as name, ( SELECT COUNT(id) FROM schedule WHERE id is not null "+cnt+") as count FROM schedule WHERE id is NOT NULL";
        // 검색 기능 쿼리문 가공(작성자 ID, 날짜, 일정내용)
        sql += (w_id == "") ? "" : " AND w_id = "+w_id; // 작성자 검색 AND 쿼리
        sql += (memo == "") ? "" : " AND memo LIKE '%"+memo+"%'"; // 메모 검색 AND 쿼리

        // 날짜 일수 검색
        sql += (date == "") ? "" : " AND DATE_FORMAT(edit_date, '%Y-%m-%d') BETWEEN DATE_SUB(DATE_FORMAT(now(), '%Y-%m-%d'), INTERVAL  "+date+" DAY) AND DATE_FORMAT(now(), '%Y-%m-%d')"; // 날짜 검색
        sql += " ORDER BY edit_date DESC"; // 레벨 2 수정일 내림차순 정렬
        sql += (limit1 == null) ? " LIMIT 0,"+count : " LIMIT "+limit1+","+limit2;
        System.out.println("[일정 검색 쿼리문 확인]");
        System.out.println(sql); // 쿼리문 출력

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id"); // 일정 ID
                int w_id = rs.getInt("w_id"); // 작성자 ID
                String memo = rs.getString("memo"); // 일정 내용
                String w_name = rs.getString("w_name"); // 작성자 이름
                String pw = rs.getString("pw"); // 일정 비밀번호
                String reg_date = rs.getString("reg_date"); // 일정 작성일
                String edit_date = rs.getString("edit_date"); // 일정 수정일
                String name = rs.getString("name"); // 작성자이름 서브쿼리 조회
                int count = rs.getInt("count");
                return new ScheduleResponseDto(id, w_id, memo, w_name, pw, reg_date, edit_date, name, count);
            }
        });
    }

    // 일정 등록한 작성자목록 조회 DB조회(SELECT) - GET
    public List<ScheduleResponseDto> selectWList() {
        // SELECT 쿼리 - GROUP BY로 일정에 등록된 작성자 목록만 추출
        String sql = "SELECT a.id, a.w_id, b.name FROM schedule a LEFT JOIN writer b ON a.w_id = b.id WHERE a.id IS NOT NULL GROUP BY a.w_id ORDER BY a.w_id ASC";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id"); // 일정 ID
                int w_id = rs.getInt("w_id"); // 작성자 ID
                String name = rs.getString("name"); // 작성자 이름
                return new ScheduleResponseDto(id, w_id, name);
            }
        });
    }

    // 일정 데이터 개수 조회 (SELECT) - GET
    public List<ScheduleResponseDto> selectCount() {
        // SELECT 쿼리 - COUNT() 집계함수를 사용 일정 데이터 개수 조회
        String sql = "SELECT COUNT(id) as count FROM schedule";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int count = rs.getInt("count"); // 일정 데이터 개수
                return new ScheduleResponseDto(count);
            }
        });
    }

    // 일정 수정 (UPDATE) - PUT
    public void update(int id, ScheduleRequestDto scheduleRequestDto) {
        // UPDATE 쿼리
        String sql = "UPDATE schedule SET w_id = ?, memo = ?, edit_date = now() WHERE id = ?";

        // [ 수정 항목 ] - 작성자 ID, 일정 내용
        // [ WHERE 조건 ] - 일정 ID
        jdbcTemplate.update(sql, scheduleRequestDto.getW_id(), scheduleRequestDto.getMemo(), id);
    }

    // 일정 삭제 (DELETE) - DELETE
    public void delete(int id) {

        // DELETE 쿼리
        String sql = "DELETE FROM schedule WHERE id = ?";
        // [ WHERE 조건 ] - 일정 ID
        jdbcTemplate.update(sql, id);
    }
}
