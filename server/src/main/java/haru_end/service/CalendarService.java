package haru_end.service;

import haru_end.dto.CalendarDTO;
import haru_end.entity.CalendarEntity;
import haru_end.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // POST
    public void saveEvent(CalendarDTO calendarDTO) {
        CalendarEntity calendarEntity = mapToEntity(calendarDTO);
        calendarRepository.save(calendarEntity);
    }

    // GET: 특정 ID로 이벤트 찾기
    public CalendarDTO findEventById(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id);
        if (calendarEntity == null) {
            return null; // 예외 처리 또는 메시지 반환 필요
        }
        return mapToDTO(calendarEntity);
    }

    // GET: 모든 이벤트 조회
    public List<CalendarDTO> findAllEvents() {
        return calendarRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // PATCH: 부분적으로 업데이트
    public void updateEventById(Long id, CalendarDTO calendarDTO) {
        CalendarEntity existingEvent = calendarRepository.findById(id);
        if (existingEvent != null) {
            // 부분 업데이트 처리
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
            if (calendarDTO.getUser_email() != null) {
                existingEvent.setUser_email(calendarDTO.getUser_email());
            }
            calendarRepository.updateById(id, existingEvent); // 업데이트된 필드 저장
        }
    }

    // DELETE: 이벤트 삭제
    public void deleteEventById(Long id) {
        calendarRepository.deleteById(id);
    }

    // DTO -> Entity 변환
    private CalendarEntity mapToEntity(CalendarDTO dto) {
        CalendarEntity entity = new CalendarEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setUser_email(dto.getUser_email());
        return entity;
    }

    // Entity -> DTO 변환
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
