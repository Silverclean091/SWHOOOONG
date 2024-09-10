package haru_end.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public abstract class JdbcRepository<T> {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<T> rowMapper;
    private final String tableName;
    private final String idColumn;

    protected JdbcRepository(JdbcTemplate jdbcTemplate, RowMapper<T> rowMapper, String tableName, String idColumn) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.tableName = tableName;
        this.idColumn = idColumn;
    }

    public void save(String insertSql, Object... params) {
        jdbcTemplate.update(insertSql, params);
    }

    public T findById(String selectSql, Long id) {
        return jdbcTemplate.queryForObject(selectSql, rowMapper, id);
    }

    public List<T> findAll(String selectAllSql) {
        return jdbcTemplate.query(selectAllSql, rowMapper);
    }

    public void update(String updateSql, Object... params) {
        jdbcTemplate.update(updateSql, params);
    }

    public void deleteById(String deleteSql, Long id) {
        jdbcTemplate.update(deleteSql, id);
    }
}
