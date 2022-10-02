package learn.techchefs.data;

import learn.techchefs.models.Unit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UnitJdbcTemplateRepositoryTest {

    @Autowired
    private UnitJdbcTemplateRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldFindAll() {
        List <Unit> all = repository.findAll();
        assertEquals(13, all.size());
    }
}