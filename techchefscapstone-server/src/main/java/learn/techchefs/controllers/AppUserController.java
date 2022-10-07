package learn.techchefs.controllers;

import learn.techchefs.domain.AppUserService;
import learn.techchefs.domain.Result;
import learn.techchefs.domain.ResultType;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/techchefs/user")
public class AppUserController {
    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Integer> findIdByUsername (@PathVariable String username) {
        Result<Integer> result = service.findIdByUsername(username);
        if (result.getResultType() == ResultType.NOT_FOUND) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }
}
