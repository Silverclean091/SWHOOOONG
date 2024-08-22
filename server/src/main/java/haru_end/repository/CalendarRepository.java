package haru_end.repository;

import haru_end.entity.CalendarEntity;

import java.util.List;

public interface CalendarRepository {
    void save(CalendarEntity calendarEntity);
    CalendarEntity findById(Long id);
    List<CalendarEntity> findAll();
    void updateById(Long id, CalendarEntity calendarEntity);
    void deleteById(Long id);
}
