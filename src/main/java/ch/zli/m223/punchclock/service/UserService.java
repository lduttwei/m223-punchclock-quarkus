package ch.zli.m223.punchclock.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;
}
