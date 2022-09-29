package learn.techchefs.data;

import learn.techchefs.models.Ingredient;
import learn.techchefs.models.RecipeIngredient;
import learn.techchefs.models.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeIngredientJdbcTemplateRepositoryTest {

    @Autowired
    private RecipeIngredientJdbcTemplateRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup) {
            hasSetup = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

    @Test
    void shouldFindByRecipe() {
        Map<Integer, Ingredient> map1 = new HashMap<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("Chicken");
        map1.put(1, ingredient);


        Map<Integer, Unit> map2 = new HashMap<>();
        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Ounces");
        unit.setAbbr("Oz");
        map2.put(2, unit);

        List <RecipeIngredient> all = repository.findByRecipe(map1, map2, 1);
        assertEquals(1, all.size());
    }

    @Test
    void shouldAddByRecipe() {
        Map<Integer, Ingredient> map1 = new HashMap<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("Chicken");
        map1.put(1, ingredient);


        Map<Integer, Unit> map2 = new HashMap<>();
        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Ounces");
        unit.setAbbr("Oz");
        map2.put(2, unit);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);

        List <RecipeIngredient> all = new ArrayList<>();
        all.add(recipeIngredient);

        repository.addByRecipe(2, all);
        assertEquals(1, repository.findByRecipe(map1, map2, 2).size());
    }

    @Test
    void shouldDeleteByRecipe() {
        boolean all = repository.deleteByRecipe(1);
        assertFalse(all);
    }

    @Test
    void shouldUpdateByRecipe() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("Chicken");
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        List <RecipeIngredient> all = new ArrayList<>();
        all.add(recipeIngredient);
        boolean update = repository.updateByRecipe(1, all);
        assertTrue(update);
    }
}