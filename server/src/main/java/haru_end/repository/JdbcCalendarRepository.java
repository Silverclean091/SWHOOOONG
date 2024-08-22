package haru_end.repository;

import haru_end.entity.CalendarEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCalendarRepository implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCalendarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CalendarEntity> calendarRowMapper = (rs, rowNum) -> {
        CalendarEntity entity = new CalendarEntity();
        entity.setId(rs.getLong("id"));
        entity.setTitle(rs.getString("title"));
        entity.setDescription(rs.getString("description"));
        entity.setStartDate(rs.getDate("start_date").toLocalDate());
        entity.setEndDate(rs.getDate("end_date").toLocalDate());
        entity.setUser_email(rs.getString("user_email"));
        return entity;
    };

    @Override
    public void save(CalendarEntity calendarEntity) {
        String sql = "INSERT INTO tb_calendars (title, description, start_date, end_date, user_email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, calendarEntity.getTitle(), calendarEntity.getDescription(), calendarEntity.getStartDate(), calendarEntity.getEndDate(), calendarEntity.getUser_email());
    }

    @Override
    public CalendarEntity findById(Long id) {
        String sql = "SELECT * FROM tb_calendars WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, calendarRowMapper, id);
    }

    @Override
    public List<CalendarEntity> findAll() {
        String sql = "SELECT * FROM tb_calendars";
        return jdbcTemplate.query(sql, calendarRowMapper);
    }

    @Override
    public void updateById(Long id, CalendarEntity calendarEntity) {
        String sql = "UPDATE tb_calendars SET title = ?, description = ?, start_date = ?, end_date = ?, user_email = ? WHERE id = ?";
        jdbcTemplate.update(sql, calendarEntity.getTitle(), calendarEntity.getDescription(), calendarEntity.getStartDate(), calendarEntity.getEndDate(), calendarEntity.getUser_email(), id);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM tb_calendars WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
