package learn.techchefs.data.mappers;

import learn.techchefs.models.Ingredient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements RowMapper <Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet rs, int i) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getInt("id"));
        ingredient.setName(rs.getString("name"));
        ingredient.setUserId(rs.getInt("user_id"));
        ingredient.setParentId(rs.getInt("parent_id"));
        ingredient.setContainsDairy(rs.getBoolean("contains_dairy"));
        ingredient.setNutBased(rs.getBoolean("nut_based"));
        if (rs.getBoolean("contains_gluten")) {
            ingredient.setContainsGluten(true);
            ingredient.setContainsSoy(rs.getBoolean("contains_soy"));
        }
        else {
            ingredient.setContainsGluten(false);
            ingredient.setContainsSoy(false);
        }
        if (rs.getBoolean("animal_based")) {
            ingredient.setAnimalBased(true);
            if (rs.getBoolean("meat")) {
                ingredient.setIsMeat(true);
                ingredient.setIsFish(rs.getBoolean("fish"));
            }
            else {
                ingredient.setIsMeat(false);
                ingredient.setIsFish(false);
            }
            ingredient.setContainsEgg(rs.getBoolean("contains_egg"));
        }
        else {
            ingredient.setAnimalBased(false);
            ingredient.setIsMeat(false);
            ingredient.setIsFish(false);
            ingredient.setContainsEgg(false);
        }
        return ingredient;
    }
}
