package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;

@ApplicationScoped
public class ProjectService {
    @Inject
    private EntityManager entityManager;

    public ProjectService() {
    }

    @Transactional
    public Project creatProject(Project project) {
        entityManager.persist(project);
        return project;
    }

    @Transactional
    public Project updateProject(Project project) {
        entityManager.merge(project);
        return project;
    }

    @Transactional
    public void remove(Long id) {
        entityManager.remove(getProject(id));
    }


    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        var query = entityManager.createQuery("FROM Project");
        return query.getResultList();
    }

    public Project getProject(Long id) {
        try {
            return entityManager.find(Project.class, id);
        } catch (Exception exception) {
            throw new BadRequestException();
        }

    }
}
