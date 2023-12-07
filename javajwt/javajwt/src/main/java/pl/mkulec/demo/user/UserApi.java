package pl.mkulec.demo.user;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {


    private final AuthenticationManager authenticationManager;

    public UserApi(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth/login")
    public String getJwt(@RequestBody AuthRequest authRequest) {

        Authentication authenticate =
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (authRequest.getEmail(), authRequest.getPassword()));

        ////jesli to sie powiedzie zostanie nam zwrocony obiekt uzytkownika eloooo


        User principal = (User) authenticate.getPrincipal();

        System.out.println(principal);

        return "";
    }
}
