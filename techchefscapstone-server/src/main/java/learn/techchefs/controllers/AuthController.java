package learn.techchefs.controllers;

import learn.techchefs.security.JwtConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController{
    private final AuthenticationManager authenticationManager;
    private final JwtConverter jwtConverter;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter jwtConverter){
        this.authenticationManager = authenticationManager;
        this.jwtConverter = jwtConverter;
    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String,String> credentials){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"),credentials.get("password"));

        try{
            Authentication authentication = authenticationManager.authenticate(authToken);

            if(authentication.isAuthenticated()){
                HashMap<String, String> map = new HashMap<>();

                User user = (User)authentication.getPrincipal();
                String token = jwtConverter.getTokenFromUser(user);
                map.put("jwt_token", token);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        }catch(AuthenticationException ex){
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

