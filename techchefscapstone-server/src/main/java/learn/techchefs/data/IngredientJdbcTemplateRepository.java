package learn.techchefs.data;

import learn.techchefs.data.mappers.IngredientMapper;
import learn.techchefs.models.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class IngredientJdbcTemplateRepository implements IngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    public IngredientJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Ingredient> findAll() {
        final String sql = "select " +
                    "id, " +
                    "name, " +
                    "user_id, " +
                    "parent_id, " +
                    "contains_dairy, " +
                    "nut_based, " +
                    "meat, " +
                    "fish, " +
                    "animal_based, " +
                    "contains_gluten, " +
                    "contains_egg, " +
                    "contains_soy " +
                "from ingredient";
        List <Ingredient> all = jdbcTemplate.query(sql, new IngredientMapper());
        HashMap <Integer, Ingredient> allMap = new HashMap<>();
        for (Ingredient ingredient : all) allMap.put(ingredient.getId(), ingredient);
        for (Ingredient ingredient: allMap.values()) {
            int parentId = ingredient.getParentId();
            if (parentId > 0) allMap.get(parentId).addSubIngredient(ingredient);
        }
        return all;
    }

    @Override
    public List <Ingredient> findByCategory(int parentId) {
        if (parentId <= 0) return new ArrayList<>();
        final String sql = "select " +
                    "id, " +
                    "name, " +
                    "user_id, " +
                    "parent_id, " +
                    "contains_dairy, " +
                    "nut_based, " +
                    "meat, " +
                    "fish, " +
                    "animal_based, " +
                    "contains_gluten, " +
                    "contains_egg, " +
                    "contains_soy " +
                "from ingredient where parent_id = ?";
        List <Ingredient> all = jdbcTemplate.query(sql, new IngredientMapper(), parentId);
        for (Ingredient ingredient : all) ingredient.setSubIngredients(findByCategory(ingredient.getId()));
        return all;
    }

    @Override
    public Ingredient findById(int id) {
        final String sql = "select " +
                    "id, " +
                    "name, " +
                    "user_id, " +
                    "parent_id, " +
                    "contains_dairy, " +
                    "nut_based, " +
                    "meat, " +
                    "fish, " +
                    "animal_based, " +
                    "contains_gluten, " +
                    "contains_egg, " +
                    "contains_soy " +
                "from ingredient where id = ?";
        Ingredient ingredient = jdbcTemplate.query(sql, new IngredientMapper(), id).stream()
                .findFirst().orElse(null);
        if (ingredient != null) ingredient.setSubIngredients(findByCategory(ingredient.getId()));
        return ingredient;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        final String sql = "insert into ingredient (name, user_id, contains_dairy, nut_based, meat, fish, animal_based, " +
                "contains_gluten, contains_egg, contains_soy) values " +
                "(?,?,?,?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ingredient.getName());
            statement.setInt(2, ingredient.getUserId());
            statement.setBoolean(3,ingredient.isContainsDairy());
            statement.setBoolean(4,ingredient.isNutBased());
            statement.setBoolean(5, ingredient.isMeat());
            statement.setBoolean(6, ingredient.isFish());
            statement.setBoolean(7, ingredient.isAnimalBased());
            statement.setBoolean(8, ingredient.isContainsGluten());
            statement.setBoolean(9, ingredient.isContainsEgg());
            statement.setBoolean(10, ingredient.isContainsSoy());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) return null;

        ingredient.setId(keyHolder.getKey().intValue());

        return ingredient;
    }

    @Override
    public boolean update(Ingredient ingredient) {
        final String sql = "update ingredient set " +
                    "name = ?, " +
                    "contains_dairy = ?, " +
                    "nut_based = ?, " +
                    "meat = ?, " +
                    "fish = ?, " +
                    "animal_based = ?, " +
                    "contains_gluten = ?, " +
                    "contains_egg = ?, " +
                    "contains_soy = ? " +
                "where id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
                ingredient.getName(),
                ingredient.isContainsDairy(),
                ingredient.isNutBased(),
                ingredient.isMeat(),
                ingredient.isFish(),
                ingredient.getAnimalBased(),
                ingredient.isContainsGluten(),
                ingredient.isContainsEgg(),
                ingredient.isContainsSoy(),
                ingredient.getId());

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "delete from ingredient where id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
