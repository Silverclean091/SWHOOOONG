package haru_end.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class calendarDTO {
    private long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}