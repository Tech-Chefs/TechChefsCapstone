package learn.techchefs.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@RestController
@RequestMapping("/api/techchefs")
public class DefaultController {
    private final Service service;

    public DefaultController(Service service) {
        this.service = service;
    }

    @GetMapping
    public List<> findAll() throws DataAccessException {
        return service.findAll();
    }

    @GetMapping("/---/{---}")
    public List<> findBy(@PathVariable String ) throws DataAccessException {
        return service.findBy();
    }

    @GetMapping("/{id}")
    public ResponseEntity<---> findById(@PathVariable int id) throws DataAccessException {
        --- --- = service.findById(id);
        if (--- == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(---, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody --- ---) throws DataAccessException {
        Result result = service.create(---);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
        }
        return new ResponseEntity<>(result.get(), HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody --- ---) throws DataAccessException {
        if (id != ---.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        Result result = service.update(---);
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
*/