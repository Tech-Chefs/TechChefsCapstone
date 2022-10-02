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
        ingredient.setParentId(rs.getInt("parent_id"));
        ingredient.setContainsDairy(rs.getBoolean("contains_dairy"));
        ingredient.setNutBased(rs.getBoolean("nut_based"));
        ingredient.setKosher(rs.getBoolean("kosher"));
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
                ingredient.setMeat(true);
                ingredient.setFish(rs.getBoolean("fish"));
            }
            else {
                ingredient.setMeat(false);
                ingredient.setFish(false);
            }
            ingredient.setContainsEgg(rs.getBoolean("contains_egg"));
        }
        else {
            ingredient.setAnimalBased(false);
            ingredient.setMeat(false);
            ingredient.setFish(false);
            ingredient.setContainsEgg(false);
        }
        return ingredient;
    }
}
