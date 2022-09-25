package learn.techchefs.data;

import learn.techchefs.data.mappers.IngredientMapper;
import learn.techchefs.models.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;

public class IngredientJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public IngredientJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List <Ingredient> findAll () {
        final String sql = "select " +
                    "id, " +
                    "name, " +
                    "parent_id, " +
                    "contains_dairy, " +
                    "nut_based, " +
                    "meat, " +
                    "fish, " +
                    "animal_based, " +
                    "contains_gluten, " +
                    "kosher, " +
                    "contains_egg, " +
                    "contains_soy " +
                "from ingredient";
        List <Ingredient> all = jdbcTemplate.query(sql, new IngredientMapper());
        HashMap <Integer, Ingredient> allMap = new HashMap<>();
        for (Ingredient ingredient : all) allMap.put(ingredient.getId(), ingredient);
        for (Ingredient ingredient: all) if (ingredient.getParent() != null) ingredient.setParent(allMap.get
                (ingredient.getParent().getId()));
        return all;
    }

    public Ingredient findById (int id) {
        final String sql = "select " +
                    "id, " +
                    "name, " +
                    "parent_id, " +
                    "contains_dairy, " +
                    "nut_based, " +
                    "meat, " +
                    "fish, " +
                    "animal_based, " +
                    "contains_gluten, " +
                    "kosher, " +
                    "contains_egg, " +
                    "contains_soy " +
                "from ingredient where id = ?";
        Ingredient ingredient = jdbcTemplate.query(sql, new IngredientMapper(), id).stream()
                .findFirst().orElse(null);
        if (ingredient != null && ingredient.getParent() != null) ingredient.setParent(findById
                (ingredient.getParent().getId()));
        return ingredient;
    }

    public Ingredient findBy
}
