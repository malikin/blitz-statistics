package malikin.github.io.blitz.statistics.rest;

import malikin.github.io.blitz.statistics.dao.EventRepository;
import malikin.github.io.blitz.statistics.entity.Event;
import malikin.github.io.blitz.statistics.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
public class EventController {

    private final EventRepository repository;
    private final EventService eventService;

    public EventController(EventRepository repository, EventService eventService) {
        this.repository = repository;
        this.eventService = eventService;
    }

    @GetMapping
    public Collection<Event> getEvents(@RequestParam(required = false) Long externalUid) {
        if (externalUid == null) {
            return repository.findAll();
        }

        return repository.findByExternalUid(externalUid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewEvent(@Valid @RequestBody Event event) {
        eventService.createOrIncrement(event);
    }
}
