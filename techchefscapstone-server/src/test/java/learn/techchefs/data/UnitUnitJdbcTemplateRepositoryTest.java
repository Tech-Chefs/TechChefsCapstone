package learn.techchefs.data;

import learn.techchefs.models.Ingredient;
import learn.techchefs.models.Measurement;
import learn.techchefs.models.Recipe;
import learn.techchefs.models.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UnitUnitJdbcTemplateRepositoryTest {

    @Autowired
    private UnitUnitJdbcTemplateRepository repository;

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
        Map<Integer, Unit> map1 = new HashMap<>();
        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Ounces");
        unit.setAbbr("Oz");
        map1.put(1, unit);
        repository.findAll(map1);

        assertEquals(1, repository.findAll(map1).size());
    }

    @Test
    void shouldFindByIngredient() {
        Map<Integer, Unit> map1 = new HashMap<>();
        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Ounces");
        unit.setAbbr("Oz");
        map1.put(1, unit);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("Onion");

        Map <Unit, List <Measurement>> test = repository.findByIngredient(map1, ingredient.getId());

        assertEquals(1, test.size());
    }

    @Test
    void shouldAdd() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("Onion");

        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Ounces");
        unit.setAbbr("Oz");

        Measurement measurement = new Measurement();
        measurement.setUnit(unit);
        measurement.setQuantity(12);

        Map.Entry<Unit, Measurement> entry = null;
        assert false;
        entry.setValue(measurement);

        boolean thisBoolean = repository.add(ingredient.getId(), entry);
        assertTrue(thisBoolean);
    }
}