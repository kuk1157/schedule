package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;
    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 등록
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto);

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule insertSchedule = scheduleRepository.insert(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    // 일정 전체조회
    public List<ScheduleResponseDto> getSchedules(String cnt, String id, String limit1, String limit2) {
        // ScheduleRepository에서 db관련 처리
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.allSelect(cnt, id, limit1, limit2);
    }

    // 일정 검색 조회
    public List<ScheduleResponseDto> getSearch(String count, String limit1, String limit2, String w_id, String memo, String date) {
        // ScheduleRepository에서 db관련 처리
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.searchFind(count, limit1, limit2, w_id, memo, date);
    }

    // 일정 등록한 작성자 목록 조회
    public List<ScheduleResponseDto> get_WList() {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return  scheduleRepository.selectWList();
    }

    // 일정 데이터 개수 조회(COUNT)
    public List<ScheduleResponseDto> getCount() {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.selectCount();
    }

    // 일정 수정
    public ScheduleResponseDto UpdateSchedule(int id, ScheduleRequestDto scheduleRequestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        scheduleRepository.update(id,scheduleRequestDto);
        Schedule schedule = new Schedule(scheduleRequestDto);
        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제 조회
    public int DeleteSchedule(int id) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        scheduleRepository.delete(id);
        return id;
    }
}
