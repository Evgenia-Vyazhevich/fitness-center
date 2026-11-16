import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import config.AppConfig;
import model.Client;
import model.Trainer;
import model.Training;
import repository.InMemoryTrainingRepository;
import repository.TrainingRepository;
import service.FitnessService;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        int maxParticipants = AppConfig.getMaxParticipants();

        LOGGER.info("{}", "Профиль приложения: " + AppConfig.getProfileName());
        LOGGER.info("{}", "Максимум участников: " + maxParticipants);

        Trainer trainer = new Trainer(1, "Женя");
        Training training = new Training(
                1,
                "Силовая тренировка",
                trainer,
                maxParticipants
        );

        TrainingRepository repository = new InMemoryTrainingRepository();
        repository.save(training);

        FitnessService service = new FitnessService(repository);

        Client client1 = new Client(1, "Женя");
        Client client2 = new Client(2, "Юля");
        Client client3 = new Client(3, "Елена");

        LOGGER.info("{}", "Запись Жени: " + service.enrollClient(1, client1));
        LOGGER.info("{}", "Запись Юли: " + service.enrollClient(1, client2));
        LOGGER.info("{}", "Запись Елены: " + service.enrollClient(1, client3));
        LOGGER.info("{}", "Количество участников: " + service.getParticipantsCount(1));
    }
}

