package haru_end.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter // private을 관리하는 메서드, private을 외부로 꺼내는 메서드, 리턴값만 존재
@Setter // private에 값을 넣는 메서드

public class CalendarEntity {
    private long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String user_email;
}