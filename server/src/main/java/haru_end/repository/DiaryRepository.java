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
        entity.setDiary_image(rs.getLong("diary_image"));
        entity.setToday_feeling(rs.getString("today_feeling"));
        entity.setIs_open(rs.getLong("is_open"));

        java.sql.Date diaryDate = rs.getDate("diary_date");
        if (diaryDate != null) {
            entity.setDiary_date(diaryDate.toLocalDate());
        } else {
            entity.setDiary_date(null);
        }

        entity.setContext(rs.getString("context"));
        entity.setUser_email(rs.getString("user_email"));

        java.sql.Date deletedAt = rs.getDate("deleted_at");
        if (deletedAt != null) {
            entity.setDeleted_at(deletedAt.toLocalDate());
        } else {
            entity.setDeleted_at(null);
        }

        return entity;
    };

    public void save(DiaryEntity diaryEntity) {
        String sql = "INSERT INTO tb_diaries (diary_image, today_feeling, is_open, diary_date, context, user_email) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, diaryEntity.getDiary_image(), diaryEntity.getToday_feeling(), diaryEntity.getIs_open(),
                diaryEntity.getDiary_date(), diaryEntity.getContext(), diaryEntity.getUser_email());
    }

    public DiaryEntity findById(Long id) {
        String sql = "SELECT * FROM tb_diaries WHERE id = ? AND deleted_at IS NULL"; // 소프트 삭제된 데이터는 제외
        return jdbcTemplate.queryForObject(sql, diaryRowMapper, id);
    }

    public List<DiaryEntity> findAll() {
        String sql = "SELECT * FROM tb_diaries WHERE deleted_at IS NULL"; // 소프트 삭제된 데이터는 제외
        return jdbcTemplate.query(sql, diaryRowMapper);
    }

    public void updateById(Long id, DiaryEntity diaryEntity) {
        String sql = "UPDATE tb_diaries SET diary_image = ?, today_feeling = ?, is_open = ?, diary_date = ?, context = ?, user_email = ? WHERE id = ?";
        jdbcTemplate.update(sql, diaryEntity.getDiary_image(), diaryEntity.getToday_feeling(), diaryEntity.getIs_open(),
                diaryEntity.getDiary_date(), diaryEntity.getContext(), diaryEntity.getUser_email(), id);
    }

    public void softDeleteById(Long id) {
        String sql = "UPDATE tb_diaries SET deleted_at = NOW() WHERE id = ?"; // 소프트 삭제
        jdbcTemplate.update(sql, id);
    }

    public void restoreDiaryById(Long id) {
        String sql = "UPDATE tb_diaries SET deleted_at = NULL WHERE id = ?"; // 소프트 삭제 복구
        jdbcTemplate.update(sql, id);
    }
}
