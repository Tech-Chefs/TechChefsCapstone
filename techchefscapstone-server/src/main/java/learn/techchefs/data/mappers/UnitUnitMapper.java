package learn.techchefs.data.mappers;

import learn.techchefs.models.Measurement;
import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UnitUnitMapper implements RowMapper <Map.Entry<Unit, Measurement>> {
    private final Map<Integer,Unit> units;

    public UnitUnitMapper(Map<Integer, Unit> units) {
        this.units = units;
    }

    public Map.Entry<Unit, Measurement> mapRow (ResultSet rs, int i) throws SQLException {
        Unit unit = units.get(rs.getInt("unit_1_id"));
        Measurement measurement = new Measurement();
        measurement.setUnit(units.get(rs.getInt("unit_2_id")));
        measurement.setQuantity(rs.getDouble("factor"));
        return Map.entry(unit, measurement);
    }
}
