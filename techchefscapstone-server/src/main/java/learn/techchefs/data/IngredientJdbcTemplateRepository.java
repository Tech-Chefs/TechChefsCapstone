package learn.techchefs.data;

import learn.techchefs.data.mappers.IngredientMapper;
import learn.techchefs.models.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    public Ingredient add (Ingredient ingredient) {
        final String sql = "insert into ingredient (name, contains_dairy, nut_based, meat, fish, animal_based, " +
                "contains_gluten, kosher, contains_egg, contains_soy) values " +
                "(?,?,?,?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ingredient.getName());
            statement.setBoolean(2,ingredient.isContainsDairy());
            statement.setBoolean(3,ingredient.isNutBased());
            if(ingredient.getAnimalBased()) {
                statement.setBoolean(6, true);
                if (ingredient.isMeat()) {
                    statement.setBoolean(4, true);
                    statement.setBoolean(5, ingredient.isFish());
                }
                statement.setBoolean(9, ingredient.isContainsEgg());
            }
            else {
                statement.setBoolean(6, false);
                statement.setBoolean(4, false);
                statement.setBoolean(5, false);
                statement.setBoolean(9, false);
            }
            if (ingredient.getContainsGluten()) {
                statement.setBoolean(7, true);
                statement.setBoolean(10, ingredient.isContainsSoy());
            }
            else {
                statement.setBoolean(7, false);
                statement.setBoolean(10, false);
            }
            statement.setBoolean(8, ingredient.isKosher());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) return null;

        ingredient.setId(keyHolder.getKey().intValue());

        return ingredient;
    }
}
