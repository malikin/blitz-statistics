package malikin.github.io.blitz.statistics.service;

import malikin.github.io.blitz.statistics.dao.EventRepository;
import malikin.github.io.blitz.statistics.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void createOrIncrement(final Event event) {
        List<Event> events = eventRepository.findByExternalUid(event.getExternalUid());

        if (events.isEmpty()) {
            eventRepository.save(event);
            return;
        }

        Event eventExisted = events.get(0);
        eventRepository.incrementCount(eventExisted.getId());
    }
}
