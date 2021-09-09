package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import ch.zli.m223.punchclock.domain.Place;
import ch.zli.m223.punchclock.service.PlaceService;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
@RolesAllowed({ "User", "Admin" })
public class EntryController {

    @Inject
    JsonWebToken jwt;

    @Inject
    EntryService entryService;
    @Inject
    UserService userService;
    @Inject
    ProjectService projectService;
    @Inject
    PlaceService placeService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> list(@Context SecurityContext ctx) {
        return entryService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces
    public void delete(@PathParam Long id, @Context SecurityContext ctx) {
        entryService.removeEntry(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry put(Entry entry, @Context SecurityContext ctx) {
        setEntry(entry);
        return entryService.updateEntry(entry);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry add(Entry entry, @Context SecurityContext ctx) {
        setEntry(entry);
        return entryService.createEntry(entry);
    }

    private void setEntry(Entry entry){
            entry.setPlace(placeService.getPlace(entry.getPlace().getId()));
            entry.setUser(userService.getUser(entry.getUser().getId()));
            entry.setProject(projectService.getProject(entry.getProject().getId()));
    }


}