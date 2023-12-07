package pl.mkulec.demo.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.mkulec.demo.user.User;
import pl.mkulec.demo.user.UserRepo;

@Configuration
public class SecurityConfig {



    private UserRepo userRepo;

    //TO REFACTOR
    @EventListener(ApplicationReadyEvent.class)
    public void saveUser() {
        User user = new User("root", getBcryptPasswordEncoder().encode("1234"));
        userRepo.save(user);
    }

    @Autowired
    public SecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }



    @Bean
    public UserDetailsService userDetailsService()
    {
        return username -> userRepo.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User with this email not found: " + username));
    }




    @Bean
    public PasswordEncoder getBcryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();//enkryptowanie hasel
    }



    @Bean
    public AuthenticationManager authorizationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/user").hasRole("USER")
                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
            return http.build();
    }
}
