package learn.techchefs.controllers;

import learn.techchefs.domain.RecipeService;
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

    /*@GetMapping("/{ingredient}")
    public List<Recipe> findByIngredients(@PathVariable List<Ingredient> ingredients) throws DataAccessException {
        return service.findByIngredients(ingredients);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) throws DataAccessException {
        Result<Recipe> result = service.findById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Recipe recipe) throws DataAccessException {
        Result<Recipe> result = service.add(recipe);
        if (result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST); // 400
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Recipe recipe) throws DataAccessException {
        if (id != recipe.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        Result<Recipe> result = service.update(recipe);
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
        Result<Recipe> result = service.delete(id);
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
