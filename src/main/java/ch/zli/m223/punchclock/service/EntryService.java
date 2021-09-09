package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Place;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    public EntryService() {
    }

    @Transactional 
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public Entry setEntry(Entry entry) {
        return entry;
    }

    @Transactional
    public Entry getEntry(Long id) {
        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public Entry remove(Long id) {
        Entry entry = getEntry(id);
        return entry;
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }
}
