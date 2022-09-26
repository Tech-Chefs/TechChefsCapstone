package learn.techchefs.data;

import learn.techchefs.data.mappers.UnitUnitMapper;
import learn.techchefs.models.Measurement;
import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitUnitJdbcTemplateRepository implements UnitUnitRepository {
    private final JdbcTemplate jdbcTemplate;
    private final Map <Integer, Unit> units;

    public UnitUnitJdbcTemplateRepository(JdbcTemplate jdbcTemplate, Map <Integer, Unit> units) {
        this.jdbcTemplate = jdbcTemplate;
        this.units = units;
    }

    @Override
    public Map <Unit, Measurement> findAll() {
        final String sql = "select " +
                    "unit_id_1, " +
                    "unit_id_2, " +
                    "factor " +
                "from unit_unit";
        return jdbcTemplate.query(sql, new UnitUnitMapper (units)).stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map <Unit, Measurement> findByIngredient(int ingredientId) {
        final String sql = "select " +
                    "unit_id_1, " +
                    "unit_id_2, " +
                    "factor " +
                "from ingredient_unit_unit " +
                "where ingredient_id = ?";
        return jdbcTemplate.query(sql, new UnitUnitMapper (units), ingredientId).stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public boolean add(int ingredientId, Map.Entry<Unit, Measurement> entry) {
        final String sql = "insert into ingredient_unit_unit (ingredient_id, unit_1_id, unit_2_id, factor) values " +
                "(?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ingredientId);
            ps.setInt(2, entry.getKey().getId());
            Measurement measurement = entry.getValue();
            ps.setInt(3, measurement.getUnit().getId());
            ps.setDouble(4, measurement.getQuantity());
            return ps;
        }, keyHolder);
        return rowsAffected > 0;
    }
}
