package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

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


    /**
     * Returns a list of each entry saved in the database.
     * @param ctx authentication
     * @return All entries
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> list(@Context SecurityContext ctx) {
        return entryService.findAll();
    }

    /**
     * Deletes a specific entry by id
     * @param id entry to delete
     * @param ctx authentication
     */
    @DELETE
    @Path("/{id}")
    @Produces
    public void delete(@PathParam Long id, @Context SecurityContext ctx) {
        entryService.removeEntry(id);
    }

    /**
     * Updates the content of a specific Entry
     * @param entry entry with applied changes
     * @param ctx authentication
     * @return updated entry
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry put(Entry entry, @Context SecurityContext ctx) {
        setEntry(entry);
        return entryService.updateEntry(entry);
    }

    /**
     * Saves an entry in the database
     * @param entry entry to save
     * @param ctx authentication
     * @return returns saved entry
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry add(Entry entry, @Context SecurityContext ctx) {
        setEntry(entry);
        return entryService.createEntry(entry);
    }

    /**
     * Adds the correct Objects to create connections.
     * @param entry
     */
    private void setEntry(Entry entry){
            entry.setPlace(placeService.getPlace(entry.getPlace().getId()));
            entry.setUser(userService.getUser(entry.getUser().getId()));
            entry.setProject(projectService.getProject(entry.getProject().getId()));
    }


}