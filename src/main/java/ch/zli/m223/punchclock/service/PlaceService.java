package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Place;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PlaceService {
    @Inject
    private EntityManager entityManager;

    public PlaceService() {
    }

    @Transactional
    public Place createPlace(Place place) {
        entityManager.persist(place);
        return place;
    }

    @SuppressWarnings("unchecked")
    public List<Place> findAll() {
        var query = entityManager.createQuery("FROM Place");
        return query.getResultList();
    }
    public boolean validatePlace(Place place){
        return entityManager.contains(place);
    }

}
