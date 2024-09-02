package haru_end.service;

import haru_end.dto.CalendarDTO;
import haru_end.entity.CalendarEntity;
import haru_end.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository  calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // POST
    public void saveEvent(CalendarDTO calendarDTO) {
        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setTitle(calendarDTO.getTitle());
        calendarEntity.setDescription(calendarDTO.getDescription());
        calendarEntity.setStartDate(calendarDTO.getStartDate());
        calendarEntity.setEndDate(calendarDTO.getEndDate());
        calendarEntity.setUser_email(calendarDTO.getUser_email());
        calendarRepository.save(calendarEntity);
    }

    // GET
    public CalendarDTO findEventById(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id);
        if (calendarEntity == null) {
            return null; // 또는 예외 처리
        }
        return mapToDTO(calendarEntity);
    }

    public List<CalendarDTO> findAllEvents() {
        return calendarRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // 데이터 부분 PATCH
    public void updateEventById(Long id, CalendarDTO calendarDTO) {
        CalendarEntity existingEvent = calendarRepository.findById(id);
        if (existingEvent != null) {
            if (calendarDTO.getTitle() != null) {
                existingEvent.setTitle(calendarDTO.getTitle());
            }
            if (calendarDTO.getDescription() != null) {
                existingEvent.setDescription(calendarDTO.getDescription());
            }
            if (calendarDTO.getStartDate() != null) {
                existingEvent.setStartDate(calendarDTO.getStartDate());
            }
            if (calendarDTO.getEndDate() != null) {
                existingEvent.setEndDate(calendarDTO.getEndDate());
            }
            if(calendarDTO.getUser_email() != null) {
                existingEvent.setUser_email(calendarDTO.getUser_email());
            }
        }
        calendarRepository.updateById(id, existingEvent); // 업데이트 할 필드만 업데이트됨
    }

    // DELETE
    public void deleteEventById(Long id) {
        calendarRepository.deleteById(id);
    }

    // entity DTO로 변환
    private CalendarDTO mapToDTO(CalendarEntity entity) {
        CalendarDTO dto = new CalendarDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setUser_email(entity.getUser_email());
        return dto;
    }
}