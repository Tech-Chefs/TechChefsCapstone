package learn.techchefs.data.mappers;

import learn.techchefs.models.Ingredient;
import learn.techchefs.models.Measurement;
import learn.techchefs.models.RecipeIngredient;
import learn.techchefs.models.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RecipeIngredientMapper implements RowMapper <RecipeIngredient> {
    private final Map <Integer, Ingredient> ingredients;
    private final Map <Integer, Unit> units;

    public RecipeIngredientMapper(Map <Integer, Ingredient> ingredients, Map <Integer, Unit> units) {
        this.ingredients = ingredients;
        this.units = units;
    }

    @Override
    public RecipeIngredient mapRow(ResultSet rs, int i) throws SQLException {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredients.get(rs.getInt("ingredient_id")));
        Measurement measurement = new Measurement();
        measurement.setQuantity(rs.getDouble("quantity"));
        measurement.setUnit(units.get(rs.getInt("unit_id")));
        recipeIngredient.setMeasurement(measurement);
        recipeIngredient.setOptional(rs.getBoolean("optional"));
        recipeIngredient.setPreparation(rs.getString("preparation"));
        return recipeIngredient;
    }
}
