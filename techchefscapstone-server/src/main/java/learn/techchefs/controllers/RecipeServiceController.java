package learn.techchefs.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/techchefs/RecipeService")
public class RecipeServiceController {
    private final RecipeService service;

    public RecipeServiceController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Recipe> findAll() throws DataAccessException {
        return service.findAll();
    }

    @GetMapping("/{ingredient}")
    public List<Recipe> findByIngredients(@PathVariable List<Ingredient> ingredients) throws DataAccessException {
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
    public ResponseEntity<?> add(@RequestBody Recipe recipe) throws DataAccessException {
        RecipeResult result = service.create(recipe);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
        }
        return new ResponseEntity<>(result.get(), HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Recipe recipe) throws DataAccessException {
        if (id != recipe.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        Result result = service.update(recipe);
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
