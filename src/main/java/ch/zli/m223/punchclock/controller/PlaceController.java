package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Place;
import ch.zli.m223.punchclock.service.PlaceService;
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

@Path("/places")
@RolesAllowed({ "User", "Admin" })
@Tag(name = "Places", description = "Handling of places")
public class PlaceController {

    @Inject
    JsonWebToken jwt;

    @Inject
    PlaceService placeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Place> list(@Context SecurityContext ctx) {
        return placeService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces
    public void delete(@PathParam Long id, @Context SecurityContext ctx){
        placeService.remove(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Place add(Place place, @Context SecurityContext ctx) {
        return placeService.createPlace(place);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Place put(Place place, @Context SecurityContext ctx) {
        return placeService.updatePlace(place);
    }
}
