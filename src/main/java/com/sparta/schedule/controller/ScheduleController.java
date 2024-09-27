package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {

//    private final JdbcTemplate jdbcTemplate;
//
//    public ScheduleController(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    @PostMapping("/schedule")
//    public ScheduleResponseDto CreateSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
//        // RequestDto - Entity
//        Schedule schedule = new Schedule(scheduleRequestDto);
//
//        // Entity - ResponseDto
//        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
//
//    }
}
