package haru_end.controller;

import haru_end.dto.CalendarDTO;
import haru_end.service.CalendarService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/calendar") // 기본 URL 경로 설정
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // POST
    @PostMapping
    public void saveEvent(@RequestBody CalendarDTO calendarDTO) {
        calendarService.saveEvent(calendarDTO);
    }

    // 특정 데이터 GET
    @GetMapping("/{id}")
    public CalendarDTO findEventById(@PathVariable Long id) {
        return calendarService.findEventById(id);
    }

    // 모든 데이터 GET
    @GetMapping
    public List<CalendarDTO> findAllEvents() {
        return calendarService.findAllEvents();
    }

    // PATCH
    @PatchMapping("/{id}")
    public void updateEventById(@PathVariable Long id, @RequestBody CalendarDTO calendarDTO) {
        calendarService.updateEventById(id, calendarDTO);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable Long id) {
        calendarService.deleteEventById(id);
    }
}
