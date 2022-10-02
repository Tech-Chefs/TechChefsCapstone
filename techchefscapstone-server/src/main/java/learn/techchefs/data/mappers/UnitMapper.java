package learn.techchefs.data.mappers;

import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitMapper implements RowMapper <Unit> {
    @Override
    public Unit mapRow (ResultSet rs, int i) throws SQLException {
        Unit unit = new Unit();
        unit.setId(rs.getInt("id"));
        unit.setName(rs.getString("long_name"));
        unit.setAbbr(rs.getString("short_name"));
        return unit;
    }
}
