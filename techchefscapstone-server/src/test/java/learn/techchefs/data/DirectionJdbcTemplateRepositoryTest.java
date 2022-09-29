package learn.techchefs.data;

import learn.techchefs.models.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DirectionJdbcTemplateRepositoryTest {

    @Autowired
    private DirectionJdbcTemplateRepository repository;

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
        List <Direction> all = repository.findByRecipe(1);
        assertEquals(1, all.size());
    }

    @Test
    void shouldAddByRecipe() {
        Direction direction = new Direction();
        direction.setInstruction("this");
        direction.setDurationMinutes(12);
        List <Direction> directionlist = new ArrayList<>();
        directionlist.add(direction);
        repository.addByRecipe(2, directionlist);
        assertEquals(directionlist, repository.findByRecipe(2));
    }

    @Test
    void shouldDeleteByRecipe() {
        boolean all = repository.deleteByRecipe(1);
        assertFalse(all);
    }

    @Test
    void shouldUpdateByRecipe() {
        Direction direction = new Direction();
        direction.setInstruction("this");
        direction.setDurationMinutes(12);
        List <Direction> directionlist = new ArrayList<>();
        directionlist.add(direction);
        boolean update = repository.updateByRecipe(1, directionlist);
        assertTrue(update);
    }
}