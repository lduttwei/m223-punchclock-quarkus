package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.ProjectService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/projects")
@Tag(name = "Projects", description = "Handling of projects")
public class ProjectController {

    @Inject
    JsonWebToken jwt;

    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> list(@Context SecurityContext ctx) {
        return projectService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces
    public void delete(Long id, @Context SecurityContext ctx) {
        projectService.remove(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project add(Project project, @Context SecurityContext ctx) {
        return projectService.creatProject(project);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project put(Project project, @Context SecurityContext ctx) {
        return projectService.updateProject(project);
    }

}
