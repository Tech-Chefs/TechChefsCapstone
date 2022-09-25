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
        ingredient.setParent(new Ingredient(rs.getInt("parent_id")));
        ingredient.setContainsDairy(rs.getBoolean("contains_dairy"));
        ingredient.setNutBased(rs.getBoolean("nut_based"));
        ingredient.setKosher(rs.getBoolean("kosher"));
        if (rs.getBoolean("contains_gluten")) {
            if (rs.getBoolean("contains_soy")) ingredient.setContainsGluten(new boolean[] {true, true});
            else ingredient.setContainsGluten(new boolean[] {false, true});
        }
        else ingredient.setContainsGluten(new boolean[] {false, false});
        if (rs.getBoolean("animal_based")) {
            if (rs.getBoolean("meat")) {
                if (rs.getBoolean("fish")) ingredient.setAnimalBased(new boolean[] {true, true});
                else ingredient.setAnimalBased(new boolean[] {true, false});
            }
            else ingredient.setAnimalBased(new boolean[] {false, true});
            ingredient.setContainsEgg(rs.getBoolean("contains_egg"));
        }
        else {
            ingredient.setAnimalBased(new boolean[] {false, false});
            ingredient.setContainsEgg(false);
        }
        return ingredient;
    }
}
