package learn.techchefs.data;

import learn.techchefs.data.mappers.RecipeIngredientMapper;
import learn.techchefs.models.Ingredient;
import learn.techchefs.models.Measurement;
import learn.techchefs.models.RecipeIngredient;
import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class RecipeIngredientJdbcTemplateRepository implements RecipeIngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecipeIngredientJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <RecipeIngredient> findByRecipe(Map <Integer, Ingredient> ingredients, Map <Integer, Unit> units,
                                                int recipeId) {
        final String sql = "select " +
                    "ingredient_id, " +
                    "quantity, " +
                    "unit_id, " +
                    "optional, " +
                    "preparation " +
                "from recipe_ingredient " +
                "where recipe_id = ?";
        return jdbcTemplate.query(sql, new RecipeIngredientMapper(ingredients, units), recipeId);
    }

    @Override
    public void addByRecipe(int recipeId, List<RecipeIngredient> recipeIngredients) {
        final String sql = "insert into recipe_ingredient " +
                    "(recipe_id, ingredient_id, quantity, unit_id, optional, preparation) values " +
                "(?, ?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        for (RecipeIngredient recipeIngredient : recipeIngredients) jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, recipeId);
            ps.setInt(2, recipeIngredient.getIngredient().getId());
            Measurement measurement = recipeIngredient.getMeasurement();
            ps.setDouble(3, measurement.getQuantity());
            ps.setInt(4, measurement.getUnit().getId());
            ps.setBoolean(5, recipeIngredient.isOptional());
            ps.setString(6, recipeIngredient.getPreparation());
            return ps;
        }, keyHolder);
    }

    @Override
    public boolean deleteByRecipe(int recipeId) {
        final String sql = "delete from recipe_ingredient where recipe_id = ?";
        return jdbcTemplate.update(sql,recipeId) > 0;
    }

    @Override
    public boolean updateByRecipe(int recipeId, List<RecipeIngredient> recipeIngredients) {
        if (!deleteByRecipe(recipeId)) return false;
        addByRecipe(recipeId, recipeIngredients);
        return true;
    }
}
