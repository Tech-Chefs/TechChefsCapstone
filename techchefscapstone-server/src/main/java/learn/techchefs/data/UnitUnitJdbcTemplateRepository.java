package learn.techchefs.data;

import learn.techchefs.data.mappers.UnitUnitMapper;
import learn.techchefs.models.Measurement;
import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitUnitJdbcTemplateRepository implements UnitUnitRepository {
    private final JdbcTemplate jdbcTemplate;
    public UnitUnitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map <Unit, List <Measurement>> findAll(Map <Integer, Unit> units) {
        final String sql = "select " +
                "unit_id_1, " +
                "unit_id_2, " +
                "factor " +
                "from unit_unit";
        Map <Unit, List <Measurement>> unitMeasurementMap = new HashMap<>();
        List <Map.Entry <Unit, Measurement>> entryList = jdbcTemplate.query(sql, new UnitUnitMapper (units));
        for (Map.Entry <Unit, Measurement> entry : entryList) {
            Unit unit1 = entry.getKey();
            Unit unit2 = entry.getValue().getUnit();
            if (!unitMeasurementMap.containsKey(unit1)) unitMeasurementMap.put(unit1, new ArrayList<>());
            if (!unitMeasurementMap.containsKey(unit2)) unitMeasurementMap.put(unit2, new ArrayList<>());
            unitMeasurementMap.get(unit1).add(entry.getValue());
            Measurement inverseMeasurement = new Measurement();
            inverseMeasurement.setUnit(unit1);
            inverseMeasurement.setQuantity(1.0 / entry.getValue().getQuantity());
            unitMeasurementMap.get(unit2).add(inverseMeasurement);
        }
        return unitMeasurementMap;
    }

    @Override
    public Map <Unit, List <Measurement>> findByIngredient(Map <Integer, Unit> units, int ingredientId) {
        Map <Unit, List <Measurement>> unitMeasurementMap = findAll(units);
        final String sql = "select " +
                "unit_id_1, " +
                "unit_id_2, " +
                "factor " +
                "from ingredient_unit_unit " +
                "where ingredient_id = ?";
        List <Map.Entry <Unit, Measurement>> entryList =
                jdbcTemplate.query(sql, new UnitUnitMapper (units), ingredientId);
        for (Map.Entry <Unit, Measurement> entry : entryList) {
            Unit unit1 = entry.getKey();
            Unit unit2 = entry.getValue().getUnit();
            if (!unitMeasurementMap.containsKey(unit1)) unitMeasurementMap.put(unit1, new ArrayList<>());
            if (!unitMeasurementMap.containsKey(unit2)) unitMeasurementMap.put(unit2, new ArrayList<>());
            unitMeasurementMap.get(unit1).add(entry.getValue());
            Measurement inverseMeasurement = new Measurement();
            inverseMeasurement.setUnit(unit1);
            inverseMeasurement.setQuantity(1.0 / entry.getValue().getQuantity());
            unitMeasurementMap.get(unit2).add(inverseMeasurement);
        }
        return unitMeasurementMap;
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