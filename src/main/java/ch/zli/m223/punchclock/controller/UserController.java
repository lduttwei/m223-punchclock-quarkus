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

    /**
     * Returns list of all existing users
     * @param ctx authentication
     * @return all users
     */
    @RolesAllowed({ "User", "Admin" })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list(@Context SecurityContext ctx) {
        return userService.findAll();
    }

    /**
     * Creates user in database
     * @param user user to add to database
     * @param ctx authentication
     * @return new user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User add(User user, @Context SecurityContext ctx) {
        return userService.creatUser(user);
    }

    /**
     * Updates existing user
     * @param user user with different values
     * @param ctx authentication
     * @return user with updated values
     */
    @RolesAllowed({ "User", "Admin" })
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User put(User user, @Context SecurityContext ctx) {
        return userService.updateUser(user);
    }

    /**
     * Removes user from database by id
     * @param id id to identify user
     * @param ctx authentication
     */
    @RolesAllowed({ "User", "Admin" })
    @DELETE
    @Path("/{id}")
    @Produces
    public void delete(@PathParam Long id, @Context SecurityContext ctx) {
        userService.remove(id);
    }
}
