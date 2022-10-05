package learn.techchefs.controllers;

import learn.techchefs.domain.FilterType;
import learn.techchefs.domain.IngredientService;
import learn.techchefs.domain.Result;
import learn.techchefs.domain.ResultType;
import learn.techchefs.models.Ingredient;
import learn.techchefs.models.Recipe;
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

    @GetMapping("/filter/{ingredientFilter}")
    public List<Ingredient> filter(@PathVariable FilterType containsDairy, FilterType nutBased, FilterType meat, FilterType fish,
                                   FilterType animalBased, FilterType containsGluten, FilterType kosher,
                                   FilterType containsEgg, FilterType containsSoy) throws DataAccessException {
        return service.filter(containsDairy, nutBased, meat, fish, animalBased, containsGluten, containsEgg, containsSoy);
    }

    @GetMapping("/substitute/{Ingredient}")
    public ResponseEntity<List<Ingredient>> findSubstitutions(@PathVariable List<Ingredient> ingredients) throws DataAccessException {
        List<Ingredient> ingredientList = service.findSubstitutions(ingredients);
        if (ingredientList == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ingredientList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Ingredient ingredient) throws DataAccessException {
        Result<Ingredient> result = service.add(ingredient);
        if (result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST); // 400
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Ingredient ingredient) throws DataAccessException {
        if (id != ingredient.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        Result<Ingredient> result = service.update(ingredient);
        if (result.getResultType() == ResultType.INVALID) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
            } else {
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST); // 400
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws DataAccessException {
        Result<Ingredient> result = service.delete(id);
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
