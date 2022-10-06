package learn.techchefs.controllers;

import learn.techchefs.domain.UnitService;
import learn.techchefs.models.Recipe;
import learn.techchefs.models.Unit;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/techchefs/units")
public class UnitController {
    private final UnitService service;

    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Unit> findAll() throws DataAccessException {
        return service.findAll();
    }
}
