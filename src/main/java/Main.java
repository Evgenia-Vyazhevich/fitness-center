import config.AppConfig;
import model.Client;
import model.Trainer;
import model.Training;
import repository.InMemoryTrainingRepository;
import repository.TrainingRepository;
import service.FitnessService;

public class Main {
    public static void main(String[] args) {
        int maxParticipants = AppConfig.getMaxParticipants();

        System.out.println("Профиль приложения: " + AppConfig.getProfileName());
        System.out.println("Максимум участников: " + maxParticipants);

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

        System.out.println("Запись Жени: " + service.enrollClient(1, client1));
        System.out.println("Запись Юли: " + service.enrollClient(1, client2));
        System.out.println("Запись Елены: " + service.enrollClient(1, client3));
        System.out.println("Количество участников: " + service.getParticipantsCount(1));
    }
}
