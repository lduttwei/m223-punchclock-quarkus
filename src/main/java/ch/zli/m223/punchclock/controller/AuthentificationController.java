package ch.zli.m223.punchclock.controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.ViewModel.LoginResultViewModel;
import ch.zli.m223.punchclock.ViewModel.LoginViewModel;
import io.smallrye.jwt.build.Jwt;

/*
* Do not use in productive environments!
*/


@Tag(name = "Authorization", description = "Sample to manage Authorization")
@Path("/auth")
public class AuthentificationController {

    @Inject
    UserService userService;
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResultViewModel login(LoginViewModel loginViewModel){

        User user;
        try {
            user = userService.getUser(loginViewModel.getUsername());
        } catch (Exception exception) {
            throw new BadRequestException();
        }
        if(loginViewModel.getPassword().equals(user.getPassword())){
            String token =
            Jwt.issuer("https://zli.ch/issuer")
              .upn("user@zli.ch")
              .groups(new HashSet<>(Arrays.asList("User", "Admin")))
              .claim(Claims.birthdate.name(), "2001-07-13")
              .expiresIn(Duration.ofHours(1))
            .sign();
            return new LoginResultViewModel(token, user.getId());
        }
        throw new NotAuthorizedException("User ["+loginViewModel.getUsername()+"] not known");
    }
}

