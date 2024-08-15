package haru_end.service;

import haru_end.dto.calendarDTO;
import haru_end.entity.calendarEntity;
import haru_end.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // 데이터 추가
    public void saveEvent(calendarDTO calendarDTO) {
        calendarEntity calendarEntity = new calendarEntity();
        calendarEntity.setTitle(calendarDTO.getTitle());
        calendarEntity.setDescription(calendarDTO.getDescription());
        calendarEntity.setStartDate(calendarDTO.getStartDate());
        calendarEntity.setEndDate(calendarDTO.getEndDate());

        calendarRepository.save(calendarEntity);
    }

    // 데이터 조회
    public calendarDTO findEventById(Long id) {
        calendarEntity calendarEntity = calendarRepository.findById(id);
        if (calendarEntity == null) {
            return null; // 또는 예외 처리
        }
        return mapToDTO(calendarEntity);
    }

    public List<calendarDTO> findAllEvents() {
        return calendarRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // 데이터 업데이트
    public void updateEventById(Long id, calendarDTO calendarDTO) {
        calendarEntity existingEvent = calendarRepository.findById(id);
        if (existingEvent != null) {
            existingEvent.setTitle(calendarDTO.getTitle());
            existingEvent.setDescription(calendarDTO.getDescription());
            existingEvent.setStartDate(calendarDTO.getStartDate());
            existingEvent.setEndDate(calendarDTO.getEndDate());

            calendarRepository.save(existingEvent); // 수정 시 저장
        }
    }

    // 데이터 삭제
    public void deleteEventById(Long id) {
        calendarRepository.deleteById(id);
    }

    // 엔티티를 DTO로 변환
    private calendarDTO mapToDTO(calendarEntity entity) {
        calendarDTO dto = new calendarDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        return dto;
    }
}
