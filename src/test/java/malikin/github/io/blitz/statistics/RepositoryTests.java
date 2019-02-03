package malikin.github.io.blitz.statistics;

import malikin.github.io.blitz.statistics.dao.EventRepository;
import malikin.github.io.blitz.statistics.entity.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

    @Autowired
    private EventRepository repository;

	@Test
	public void createSaveAndFindNote() {

	    Event testEvent = new Event("testEvent", 3002L);
	    repository.save(testEvent);
	    List<Event> notesFounded = repository.findByExternalUid(3002L);

	    assertFalse(notesFounded.isEmpty());
	}
}
