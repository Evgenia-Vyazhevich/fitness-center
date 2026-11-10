import model.Client;
import model.Trainer;
import model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.InMemoryTrainingRepository;
import repository.TrainingRepository;
import service.FitnessService;

import static org.junit.jupiter.api.Assertions.*;

class FitnessServiceTest {
    private FitnessService service;

    @BeforeEach
    void setUp() {
        Trainer trainer = new Trainer(1, "Женя Вяжевич");
        Training training = new Training(1, "Йога", trainer, 2);

        TrainingRepository repository = new InMemoryTrainingRepository();
        repository.save(training);

        service = new FitnessService(repository);
    }

    @Test
    void shouldEnrollClientSuccessfully() {
        Client client = new Client(1, "Женя");

        boolean result = service.enrollClient(1, client);

        assertTrue(result);
        assertEquals(1, service.getParticipantsCount(1));
    }

    @Test
    void shouldNotEnrollSameClientTwice() {
        Client client = new Client(1, "Женя");

        assertTrue(service.enrollClient(1, client));
        assertTrue(service.enrollClient(1, client));
        assertEquals(1, service.getParticipantsCount(1));
    }

    @Test
    void shouldNotEnrollClientWhenTrainingIsFull() {
        Client client1 = new Client(1, "Женя");
        Client client2 = new Client(2, "Юля");
        Client client3 = new Client(3, "Елена");//new

        assertTrue(service.enrollClient(1, client1));
        assertTrue(service.enrollClient(1, client2));

        assertFalse(service.enrollClient(1, client3));
        assertEquals(2, service.getParticipantsCount(1));
    }


        @Test
    void shouldNotEnrollSameClientTwice() {
        Client client = new Client(1, "Женя");

        assertTrue(service.enrollClient(1, client));
        assertFalse(service.enrollClient(1, client));
        assertEquals(1, service.getParticipantsCount(1));
    }
}
