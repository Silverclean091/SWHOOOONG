package haru_end.repository;

import haru_end.entity.calendarEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class memoryCalendarRepository implements CalendarRepository {

    private static final Map<Long, calendarEntity> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0);

    @Override
    public void save(calendarEntity calendarEntity) {
        long id = sequence.incrementAndGet(); // ID를 생성하고 calendarEntity에 설정
        calendarEntity.setId(id); // 생성된 ID를 calendarEntity에 설정
        store.put(id, calendarEntity); // store에 저장
    }

    @Override
    public calendarEntity findById(Long id) {
        return store.get(id); // ID에 해당하는 calendarEntity를 반환
    }

    @Override
    public List<calendarEntity> findAll() {
        return store.values().stream().collect(Collectors.toList()); // store에 있는 모든 값을 리스트로 반환
    }

    @Override
    public void updateById(Long id, calendarEntity calendarEntity) {
        store.put(id, calendarEntity); // store에 저장, ID는 그대로 두고 내용만 수정
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id); // ID에 해당하는 calendarEntity를 삭제
    }
}
