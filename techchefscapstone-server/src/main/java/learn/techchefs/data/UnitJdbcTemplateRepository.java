package learn.techchefs.data;

import learn.techchefs.data.mappers.UnitMapper;
import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UnitJdbcTemplateRepository implements UnitRepository {
    private final JdbcTemplate jdbcTemplate;

    public UnitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Unit> findAll() {
        final String sql = "select " +
                    "id, " +
                    "long_name, " +
                    "short_name " +
                "from unit";
        return jdbcTemplate.query(sql, new UnitMapper());
    }
}
