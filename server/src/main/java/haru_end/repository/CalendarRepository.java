package haru_end.repository;

import haru_end.entity.calendarEntity;

import java.util.List;

public interface CalendarRepository {
    void save(calendarEntity calendarEntity);
    calendarEntity findById(Long id);
    List<calendarEntity> findAll();
    void updateById(Long id, calendarEntity calendarEntity);
    void deleteById(Long id);
}
