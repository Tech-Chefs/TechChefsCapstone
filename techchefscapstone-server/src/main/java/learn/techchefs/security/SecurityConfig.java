package learn.techchefs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtConverter jwtConverter;

    public SecurityConfig(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/techchefs", "/api/techchefs/**", "/api/techchefs/*/*").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/techchefs/RecipeService","/api/techchefs/IngredientService").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/api/techchefs/RecipeService/*","/api/techchefs/IngredientService/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/api/techchefs/RecipeService/*").hasAnyRole("ADMIN")
                // if we get to this point, let's deny all requests
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), jwtConverter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
