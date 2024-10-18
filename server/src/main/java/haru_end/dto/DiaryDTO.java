package haru_end.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryDTO {
    private long id;
    private long diary_image;
    private String today_feeling;
    private long is_open;
    private LocalDate diary_date;
    private String context;
    private String user_email;
    private LocalDate deleted_at;
}