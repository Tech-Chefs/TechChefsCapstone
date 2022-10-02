package learn.techchefs.data;

import learn.techchefs.models.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IngredientJdbcTemplateRepositoryTest {

    @Autowired
    private IngredientJdbcTemplateRepository repository;

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
        List <Ingredient> all = repository.findAll();
        assertTrue(all.size() >= 8 && all.size() <= 10);
    }

    @Test
    void shouldFindByCategory() {
        List <Ingredient> tomatoes = repository.findByCategory(1);
        assertEquals(1, tomatoes.size());
    }

    @Test
    void shouldFindById() {
        Ingredient tomato = repository.findById(1);
        assertEquals("Tomato", tomato.getName());
        assertEquals(1, tomato.getSubIngredients().size());
    }

    @Test
    void shouldFindSoyById() {
        Ingredient soySauce = repository.findById(8);
        assertTrue(soySauce.isContainsSoy());
    }

    @Test
    void shouldFindFishById() {
        Ingredient salmon = repository.findById(5);
        assertTrue(salmon.isFish());
    }

    @Test
    void shouldNotFindByInvalidId () {
        Ingredient unexpected = repository.findById(99);
        assertEquals(null, unexpected);
    }

    @Test
    void shouldAdd() {
        Ingredient lettuce = new Ingredient();
        lettuce.setName("Lettuce");
        assertEquals(10, repository.add(lettuce).getId());
    }

    @Test
    void shouldUpdate() {
        Ingredient expected = repository.findById(3);
        expected.setName("Peanuts");
        assertTrue(repository.update(expected));
        assertEquals(expected, repository.findById(3));
    }

    @Test
    void shouldNotUpdateByInvalidId() {
        Ingredient unexpected = new Ingredient();
        unexpected.setId(99);
        unexpected.setName("Onion");
        assertFalse(repository.update(unexpected));
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(2));
        assertNull(repository.findById(2));
    }

    @Test
    void shouldNotDeleteByInvalidId() {
        assertFalse(repository.deleteById(0));
    }
}