package malikin.github.io.blitz.statistics.rest;

import malikin.github.io.blitz.statistics.entity.Event;
import malikin.github.io.blitz.statistics.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Collection<Event> getEvents(@RequestParam Long externalUid) {
        return eventService.findByExternalUid(externalUid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewEvent(@Valid @RequestBody Event event) {
        eventService.createOrIncrement(event);
    }
}
