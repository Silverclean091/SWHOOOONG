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

        // diary_date가 null인지 체크한 후 toLocalDate()를 호출합니다.
        java.sql.Date diaryDate = rs.getDate("diary_date");
        if (diaryDate != null) {
            entity.setDiary_date(diaryDate.toLocalDate());
        } else {
            entity.setDiary_date(null); // 또는 적절한 기본값 설정
        }

        entity.setContext(rs.getString("context"));
        entity.setUser_email(rs.getString("user_email"));

        // deleted_at 필드도 동일하게 처리
        java.sql.Date deletedAt = rs.getDate("deleted_at");
        if (deletedAt != null) {
            entity.setDeleted_at(deletedAt.toLocalDate());
        } else {
            entity.setDeleted_at(null); // 또는 적절한 기본값 설정
        }

        return entity;
    };

    public void save(DiaryEntity diaryEntity) {
        String sql = "INSERT INTO tb_diaries (image, today_feeling, is_open, diary_date, context, user_email) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, diaryEntity.getDiary_image(), diaryEntity.getToday_feeling(), diaryEntity.getIs_open(),
                diaryEntity.getDiary_date(), diaryEntity.getContext(), diaryEntity.getUser_email());
    }

    public DiaryEntity findById(Long id) {
        String sql = "SELECT * FROM tb_diaries WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, diaryRowMapper, id);
    }

    public List<DiaryEntity> findAll() {
        String sql = "SELECT * FROM tb_diaries WHERE deleted = FALSE";
        return jdbcTemplate.query(sql, diaryRowMapper);
    }

    public void updateById(Long id, DiaryEntity diaryEntity) {
        String sql = "UPDATE tb_diaries SET image = ?, today_feeling = ?, is_open = ?, diary_date = ?, context = ?, user_email = ? WHERE id = ?";
        jdbcTemplate.update(sql, diaryEntity.getDiary_image(), diaryEntity.getToday_feeling(), diaryEntity.getIs_open(),
                diaryEntity.getDiary_date(), diaryEntity.getContext(), diaryEntity.getUser_email(), id);
    }

    public void softDeleteById(Long id) {
        String sql = "UPDATE tb_diaries SET deleted = TRUE WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
