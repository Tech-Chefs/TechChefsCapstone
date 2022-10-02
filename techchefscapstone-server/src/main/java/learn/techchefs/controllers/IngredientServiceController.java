package learn.techchefs.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/techchefs/IngredientService")
public class IngredientServiceController {
    private final IngredientService service;

    public IngredientServiceController(IngredientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ingredient> findAll() throws DataAccessException {
        return service.findAll();
    }

    @GetMapping("/{ingredientFilter}")
    public List<Ingredient> filter(@PathVariable FilterType containsDairy, FilterType nutBased, FilterType meat, FilterType fish,
                                   FilterType animalBased, FilterType containsGluten, FilterType kosher,
                                   FilterType containsEgg, FilterType containsSoy) throws DataAccessException {
        return service.findByIngregients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable int id) throws DataAccessException {
        Recipe recipe = service.findById(id);
        if (recipe == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Ingredient ingredient) throws DataAccessException {
        Result result = service.add(ingredient);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
        }
        return new ResponseEntity<>(result.get(), HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Ingredient ingredient) throws DataAccessException {
        if (id != ingredient.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        Result result = service.update(ingredient);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws DataAccessException {
        Result result = service.deleteById(id);
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
