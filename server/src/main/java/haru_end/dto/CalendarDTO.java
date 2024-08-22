package haru_end.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CalendarDTO {
    private long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String user_email;
}