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

    // 데이터 추가 (POST)
    public void saveEvent(calendarDTO calendarDTO) {
        calendarEntity calendarEntity = new calendarEntity();
        calendarEntity.setTitle(calendarDTO.getTitle());
        calendarEntity.setDescription(calendarDTO.getDescription());
        calendarEntity.setStartDate(calendarDTO.getStartDate());
        calendarEntity.setEndDate(calendarDTO.getEndDate());

        calendarRepository.save(calendarEntity);
    }

    // 데이터 조회 (GET)
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

    // 데이터 부분 필드 업데이트 (PATCH)
    public void updateEventById(Long id, calendarDTO calendarDTO) {
        calendarEntity existingEvent = calendarRepository.findById(id);
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
        }
        calendarRepository.updateById(id, existingEvent); // 업데이트 할 필드만 업데이트됨
    }

    //데이터 전체 업데이트 (PUT)
    public void replaceEventById(Long id, calendarDTO calendarDTO) {
        calendarEntity existingEvent = calendarRepository.findById(id);
        if(existingEvent != null) {
            existingEvent.setTitle(calendarDTO.getTitle());
            existingEvent.setDescription(calendarDTO.getDescription());
            existingEvent.setStartDate(calendarDTO.getStartDate());
            existingEvent.setEndDate(calendarDTO.getEndDate());

            calendarRepository.updateById(id, existingEvent);
        }
    }

    // 데이터 삭제 (DELETE)
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
