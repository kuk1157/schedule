package com.sparta.schedule.controller;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import com.sparta.schedule.service.ScheduleService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    // jdbcTemplate 불러오기
    private final JdbcTemplate jdbcTemplate;
    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정등록 분리
    @PostMapping("/schedule")
    public ScheduleResponseDto CreateSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    // 일정 전체조회 분리
    // 일정상세보기, 페이징, 검색 초기화 3개 기능에서 해당 API 사용중
    // cnt : 일정 게시글 기본 개수 - getSchedules(함수명)
    // id : 일정 상세보기 일정 ID - getSchedules(함수명)
    // limit1 : 페이징 limit 첫번째 값 - getLimit(함수명)
    // limit2 : 페이징 limit 두번째 값 - getLimit(함수명)
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedules(String cnt, String id, String limit1, String limit2) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getSchedules(cnt, id, limit1, limit2);
    }

    // 일정 검색 조회 분리
    // cnt : 일정 게시글 기본 개수 - searchGet(함수명)
    // limit1 : 페이징 limit 첫번째 값 - getLimitSearch(함수명)
    // limit2 : 페이징 limit 두번째 값 - getLimitSearch(함수명)
    // w_id : 작성자 ID(select) - searchGet(함수명)
    // memo : 일정 내용(input) - searchGet(함수명)
    // date : 기간검색(select) - searchGet(함수명)
    @GetMapping("/schedule/search")
    public List<ScheduleResponseDto> getSearch(String count, String limit1, String limit2, @RequestParam(required = false) String w_id, @RequestParam(required = false) String memo, @RequestParam(required = false) String date) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getSearch(count, limit1, limit2, w_id, memo, date);
    }

    // 등록된 작성자 조회 분리
    @GetMapping("/schedule/wlist")
    public List<ScheduleResponseDto> get_WList() {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.get_WList();
    }

    // 일정 데이터 개수 조회(COUNT)
    @GetMapping("/schedule/listcount")
    public List<ScheduleResponseDto> getCount() {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getCount();
    }

    // 업데이트 분리
    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto UpdateSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.UpdateSchedule(id, scheduleRequestDto);
    }

    // 삭제 분리
    @DeleteMapping("/schedule/{id}")
    public int DeleteSchedule(@PathVariable int id) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.DeleteSchedule(id);
    }
}
