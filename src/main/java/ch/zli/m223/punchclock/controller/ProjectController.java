package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.ProjectService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/projects")
@RolesAllowed({ "User", "Admin" })

@Tag(name = "Projects", description = "Handling of projects")
public class ProjectController {

    @Inject
    JsonWebToken jwt;

    @Inject
    ProjectService projectService;

    /**
     * Returns all projects saved in database
     * @param ctx authentication
     * @return all projects
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> list(@Context SecurityContext ctx) {
        return projectService.findAll();
    }

    /**
     * Removes specific project by id
     * @param id id to identify project
     * @param ctx authentication
     */
    @DELETE
    @Path("/{id}")
    @Produces (MediaType.TEXT_PLAIN)
    public void delete(@PathParam Long id, @Context SecurityContext ctx) {
        projectService.remove(id);
    }

    /**
     * Adds a project to the database
     * @param project project to add
     * @param ctx authentication
     * @return added project
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project add(Project project, @Context SecurityContext ctx) {
        return projectService.creatProject(project);
    }

    /**
     * updates existing project
     * @param project project with new values
     * @param ctx authentication
     * @return new project
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project put(Project project, @Context SecurityContext ctx) {
        return projectService.updateProject(project);
    }

}
