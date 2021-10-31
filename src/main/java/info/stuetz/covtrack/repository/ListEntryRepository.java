package info.stuetz.covtrack.repository;

import info.stuetz.covtrack.entity.ListEntry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ListEntryRepository implements PanacheRepository<ListEntry> {
    @Inject
    EntityManager em;

    public ListEntryRepository() {
    }
}
