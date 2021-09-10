package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/users")
@Tag(name = "Users", description = "Handling of users")
public class UserController {
    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    @RolesAllowed({ "User", "Admin" })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list(@Context SecurityContext ctx) {
        return userService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User add(User user, @Context SecurityContext ctx) {
        return userService.creatUser(user);
    }

    @RolesAllowed({ "User", "Admin" })
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User put(User user, @Context SecurityContext ctx) {
        return userService.updateUser(user);
    }

    @RolesAllowed({ "User", "Admin" })
    @DELETE
    @Path("/{id}")
    @Produces
    public void delete(@PathParam Long id, @Context SecurityContext ctx) {
        userService.remove(id);
    }
}
