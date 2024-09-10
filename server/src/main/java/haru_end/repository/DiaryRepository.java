package haru_end.repository;

import haru_end.entity.DiaryEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiaryRepository {

    private final JdbcTemplate jdbcTemplate;

    public DiaryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<DiaryEntity> diaryRowMapper = (rs, rowNum) -> {
        DiaryEntity entity = new DiaryEntity();
        entity.setId(rs.getLong("id"));
        entity.setImage(rs.getLong("image"));
        entity.setToday_feeling(rs.getString("today_feeling"));
        entity.setIs_open(rs.getLong("is_open"));
        entity.setDiary_date(rs.getDate("diary_date").toLocalDate());
        entity.setContext(rs.getString("context"));
        entity.setUser_email(rs.getString("user_email"));
        return entity;
    };

    public void save(DiaryEntity diaryEntity) {
        String sql = "INSERT INTO tb_diaries (image, today_feeling, is_open, diary_date, context, user_email) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, diaryEntity.getImage(), diaryEntity.getToday_feeling(), diaryEntity.getIs_open(),
                diaryEntity.getDiary_date(), diaryEntity.getContext(), diaryEntity.getUser_email());
    }

    public DiaryEntity findById(Long id) {
        String sql = "SELECT * FROM tb_diaries WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, diaryRowMapper, id);
    }

    public List<DiaryEntity> findAll() {
        String sql = "SELECT * FROM tb_diaries";
        return jdbcTemplate.query(sql, diaryRowMapper);
    }

    public void updateById(Long id, DiaryEntity diaryEntity) {
        String sql = "UPDATE tb_diaries SET image = ?, today_feeling = ?, is_open = ?, diary_date = ?, context = ?, user_email = ? WHERE id = ?";
        jdbcTemplate.update(sql, diaryEntity.getImage(), diaryEntity.getToday_feeling(), diaryEntity.getIs_open(),
                diaryEntity.getDiary_date(), diaryEntity.getContext(), diaryEntity.getUser_email(), id);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM tb_diaries WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
