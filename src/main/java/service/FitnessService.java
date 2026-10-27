package service;

import model.Client;
import model.Training;
import repository.TrainingRepository;

public class FitnessService {
    private final TrainingRepository trainingRepository;

    public FitnessService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public boolean enrollClient(int trainingId, Client client) {
        Training training = trainingRepository.findById(trainingId);

        if (training == null) {
            throw new IllegalArgumentException("Тренировка не найдена");
        }

        return training.addParticipant(client);
    }

    public int getParticipantsCount(int trainingId) {
        Training training = trainingRepository.findById(trainingId);

        if (training == null) {
            throw new IllegalArgumentException("Тренировка не найдена");
        }

        return training.getParticipantsCount();
    }
}
