package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import ch.zli.m223.punchclock.domain.Place;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.PlaceService;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
@RolesAllowed({ "User", "Admin" })
public class EntryController {

    @Inject
    JsonWebToken jwt;

    @Inject
    EntryService entryService;
    UserService userService;
    ProjectService projectService;
    PlaceService placeService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> list(@Context SecurityContext ctx) {
        return entryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry add(Entry entry, @Context SecurityContext ctx) {
        if ( validate(entry) ){
            throw new BadRequestException();
        }
       return entryService.createEntry(entry);
    }
    public boolean validate(Entry entry){
        if ( !placeService.validatePlace(entry.getPlace())){
            return false;
        }
        if ( !projectService.validateProject(entry.getProject())) {
            return false;
        }
        return userService.validateUser(entry.getUser());
    }





}
