import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import model.Client;
import model.Trainer;
import model.Training;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import repository.InMemoryTrainingRepository;
import repository.TrainingRepository;
import service.FitnessService;

import static org.junit.jupiter.api.Assertions.*;

class FitnessLoggingTest {
    @Test
    void shouldLogSuccessFullTrainingAndMissingTraining() {
        Logger logger = (Logger) LoggerFactory.getLogger(FitnessService.class);
        ListAppender<ILoggingEvent> events = new ListAppender<>();
        events.start();
        logger.addAppender(events);
        try {
            TrainingRepository repository = new InMemoryTrainingRepository();
            repository.save(new Training(1, "Yoga", new Trainer(1, "Trainer"), 1));
            FitnessService service = new FitnessService(repository);
            assertTrue(service.enrollClient(1, new Client(1, "Client 1")));
            assertFalse(service.enrollClient(1, new Client(2, "Client 2")));
            assertThrows(IllegalArgumentException.class,
                    () -> service.enrollClient(999, new Client(3, "Client 3")));
            assertEquals(1, service.getParticipantsCount(1));
            assertEquals(3, events.list.size());
            assertEquals(Level.INFO, events.list.get(0).getLevel());
            assertEquals(Level.WARN, events.list.get(1).getLevel());
            assertTrue(events.list.get(1).getFormattedMessage().contains("No free places"));
            assertEquals(Level.ERROR, events.list.get(2).getLevel());
            assertNotNull(events.list.get(2).getThrowableProxy());
        } finally {
            logger.detachAppender(events);
            events.stop();
        }
    }

    @Test
    void shouldPreserveRepositoryFailure() {
        IllegalStateException failure = new IllegalStateException("Storage unavailable");
        TrainingRepository repository = new TrainingRepository() {
            @Override
            public void save(Training training) {
                throw failure;
            }

            @Override
            public Training findById(int id) {
                throw failure;
            }
        };
        FitnessService service = new FitnessService(repository);
        assertSame(failure, assertThrows(IllegalStateException.class,
                () -> service.enrollClient(1, new Client(1, "Client"))));
    }
}
