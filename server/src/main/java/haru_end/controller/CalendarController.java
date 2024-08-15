package haru_end.controller;

import haru_end.dto.calendarDTO;
import haru_end.service.CalendarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar") // 기본 URL 경로 설정
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 데이터 추가 (POST)
    @PostMapping
    public void saveEvent(@RequestBody calendarDTO calendarDTO) {
        calendarService.saveEvent(calendarDTO);
    }

    // 특정 데이터 조회 (GET)
    @GetMapping("/{id}")
    public calendarDTO findEventById(@PathVariable Long id) {
        return calendarService.findEventById(id);
    }

    // 모든 데이터 조회 (GET)
    @GetMapping
    public List<calendarDTO> findAllEvents() {
        return calendarService.findAllEvents();
    }

    // 데이터 업데이트 (PATCH)
    @PatchMapping("/{id}")
    public void updateEventById(@PathVariable Long id, @RequestBody calendarDTO calendarDTO) {
        calendarService.updateEventById(id, calendarDTO);
    }

    // 데이터 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable Long id) {
        calendarService.deleteEventById(id);
    }
}
