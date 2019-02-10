package malikin.github.io.blitz.statistics.dao;

import malikin.github.io.blitz.statistics.entity.Event;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    @Cacheable("eventsAll")
    List<Event> findAll();

    @Cacheable("eventsByExternalUid")
    List<Event> findByExternalUid(Long externalUid);

    @Modifying
    @Query("update Event e set e.count = e.count + 1 where e.id = :eventId")
    void incrementCount(@Param("eventId") Long eventId);
}
