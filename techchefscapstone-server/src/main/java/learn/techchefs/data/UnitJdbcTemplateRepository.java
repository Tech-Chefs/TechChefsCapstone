package learn.techchefs.data;

import learn.techchefs.data.mappers.UnitMapper;
import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UnitJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public UnitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Unit> findAll () {
        final String sql = "select " +
                    "id, " +
                    "long_name, " +
                    "short_name " +
                "from unit";
        return jdbcTemplate.query(sql, new UnitMapper());
    }
}
