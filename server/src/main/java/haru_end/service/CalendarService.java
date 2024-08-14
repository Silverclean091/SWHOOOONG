package com.example.calendar.service;

import com.example.calendar.dto.CalendarEventDTO;
import com.example.calendar.entity.CalendarEvent;
import com.example.calendar.repository.CalendarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarEventService {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    // DTO -> Entity 변환
    private CalendarEvent convertToEntity(CalendarEventDTO dto) {
        CalendarEvent event = new CalendarEvent();
        event.setId(dto.getId());
        event.setUserEmail(dto.getUserEmail());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartDate(dto.getStartDate());
        event.setEndDate(dto.getEndDate());
        return event;
    }

    // Entity -> DTO 변환
    private CalendarEventDTO convertToDTO(CalendarEvent event) {
        CalendarEventDTO dto = new CalendarEventDTO();
        dto.setId(event.getId());
        dto.setUserEmail(event.getUserEmail());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStartDate(event.getStartDate());
        dto.setEndDate(event.getEndDate());
        return dto;
    }

    public List<CalendarEventDTO> getEventsByUserEmail(String userEmail) {
        List<CalendarEvent> events = calendarEventRepository.findByUserEmail(userEmail);
        return events.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<CalendarEventDTO> getEventById(Long id) {
        Optional<CalendarEvent> event = calendarEventRepository.findById(id);
        return event.map(this::convertToDTO);
    }

    public CalendarEventDTO createEvent(CalendarEventDTO eventDTO) {
        CalendarEvent event = convertToEntity(eventDTO);
        CalendarEvent savedEvent = calendarEventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public Optional<CalendarEventDTO> updateEvent(Long id, CalendarEventDTO eventDTO) {
        return calendarEventRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setTitle(eventDTO.getTitle());
                    existingEvent.setDescription(eventDTO.getDescription());
                    existingEvent.setStartDate(eventDTO.getStartDate());
                    existingEvent.setEndDate(eventDTO.getEndDate());
                    CalendarEvent updatedEvent = calendarEventRepository.save(existingEvent);
                    return convertToDTO(updatedEvent);
                });
    }

    public void deleteEvent(Long id) {
        calendarEventRepository.deleteById(id);
    }
}
