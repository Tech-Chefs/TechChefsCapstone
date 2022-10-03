package learn.techchefs.data;

import learn.techchefs.data.mappers.DirectionMapper;
import learn.techchefs.models.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DirectionJdbcTemplateRepository implements DirectionRepository {
    private final JdbcTemplate jdbcTemplate;

    public DirectionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Direction> findByRecipe(int recipeId) {
        final String sql = "select " +
                    "instruction, " +
                    "num_minutes " +
                "from step " +
                "where recipe_id = ? " +
                "order by step_num";
        return jdbcTemplate.query(sql, new DirectionMapper());
    }

    @Override
    public void addByRecipe(int recipeId, List<Direction> directions) {
        final String sql = "insert into step (recipe_id, step_num, instruction, num_minutes) values " +
                "(?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        for (int i = 0; i < directions.size(); i++) {
            int finalI = i;
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, recipeId);
                statement.setInt(2, (finalI + 1));
                statement.setString(3, directions.get(finalI).getInstruction());
                statement.setInt(4, directions.get(finalI).getDurationMinutes());
                return statement;
            },keyHolder);
        }
    }

    @Override
    public boolean deleteByRecipe(int recipeId) {
        final String sql = "delete from step where recipe_id = ?";
        return jdbcTemplate.update(sql,recipeId) > 0;
    }

    @Override
    public boolean updateByRecipe(int recipeId, List<Direction> directions) {
        if (!deleteByRecipe(recipeId)) return false;
        addByRecipe(recipeId, directions);
        return true;
    }
}
