package haru_end.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DiaryEntity {
    private long id;
    private long diary_image;
    private String today_feeling;
    private long is_open;
    private LocalDate diary_date;
    private String context;
    private String user_email;
    private LocalDate deleted_at;
}