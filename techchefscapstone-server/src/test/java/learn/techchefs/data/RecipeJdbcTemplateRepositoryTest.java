package learn.techchefs.data;

import learn.techchefs.models.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeJdbcTemplateRepositoryTest {

    @Autowired
    private RecipeJdbcTemplateRepository repository;

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
    void shouldFindAll() {
        List <Recipe> all = repository.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void shouldFindById() {
        Recipe recipe = repository.findById(1);
        assertEquals(1, recipe.getId());
    }

    @Test
    void shouldAddByRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(2);
        recipe.setName("test");
        recipe.setDescription("test");
        repository.add(recipe);
        assertEquals("test", repository.findById(2).getName());
    }

    @Test
    void shouldDeleteByRecipe() {
        boolean all = repository.deleteById(1);
        assertFalse(all);
    }

    @Test
    void shouldUpdateByRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("UpdateTest");
        recipe.setDescription("UpdateTest");
        boolean update = repository.update(recipe);
        assertTrue(update);
    }

}