package haru_end.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter // private을 관리하는 메서드, private을 외부로 꺼내는 메서드, 리턴값만 존재
@Setter // private에 값을 넣는 메서드

public class calendarEntity {
    private long id; // 캘린더 id
    private String title; // 캘린더 제목
    private String description; // 캘린더 짧은설명
    private LocalDate startDate; // 캘린더 시작날짜
    private LocalDate endDate; // 캘린더 종료날짜
}