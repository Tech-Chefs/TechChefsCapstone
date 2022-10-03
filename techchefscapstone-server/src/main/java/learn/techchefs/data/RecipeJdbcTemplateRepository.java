package learn.techchefs.data;

import learn.techchefs.data.mappers.RecipeMapper;
import learn.techchefs.models.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RecipeJdbcTemplateRepository implements RecipeRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecipeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Recipe> findAll() {
        final String sql = "select " +
                    "id, " +
                    "name, " +
                    "description " +
                "from recipe";
        return jdbcTemplate.query(sql, new RecipeMapper());
    }

    @Override
    public Recipe findById(int id) {
        final String sql = "select " +
                    "id, " +
                    "name, " +
                    "description " +
                "from recipe " +
                "where id = ?";
        return jdbcTemplate.query(sql, new RecipeMapper(), id).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Recipe add(Recipe recipe) {
        final String sql = "insert into recipe (name, description) values " +
                "(?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getDescription());
            return ps;
        }, keyHolder);
        if (rowsAffected == 0) return null;
        recipe.setId(keyHolder.getKey().intValue());
        return recipe;
    }

    @Override
    public boolean update(Recipe recipe) {
        final String sql = "update recipe set " +
                    "name = ?, " +
                    "description = ? " +
                "where id = ?";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(sql, recipe.getName(), recipe.getDescription(), recipe.getId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int recipeId) {
        final String sql = "delete from recipe where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, recipeId);
        return rowsAffected > 0;
    }
}
