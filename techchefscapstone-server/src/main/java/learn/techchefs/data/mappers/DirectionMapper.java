package learn.techchefs.data.mappers;

import learn.techchefs.models.Direction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectionMapper implements RowMapper <Direction> {

    @Override
    public Direction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Direction direction = new Direction();
        direction.setInstruction(rs.getString("instruction"));
        direction.setDurationMinutes(rs.getInt("num_minutes"));
        return direction;
    }
}
