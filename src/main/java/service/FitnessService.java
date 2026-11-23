package service;

import model.Client;
import model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.TrainingRepository;

public class FitnessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FitnessService.class);
    private final TrainingRepository trainingRepository;

    public FitnessService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public boolean enrollClient(int trainingId, Client client) {
        try {
            Training training = trainingRepository.findById(trainingId);
            if (training == null) {
                throw new IllegalArgumentException("Тренировка не найдена");
            }

            boolean enrolled = training.addParticipant(client);
            if (enrolled) {
                LOGGER.info("Client {} enrolled in training {}", client.getId(), trainingId);
            } else if (training.getParticipantsCount() >= training.getMaxParticipants()) {
                LOGGER.warn("No free places in training {} for client {}",
                        trainingId, client.getId());
            } else {
                LOGGER.warn("Client {} is already enrolled in training {}",
                        client.getId(), trainingId);
            }
            return enrolled;
        } catch (RuntimeException exception) {
            LOGGER.error("Enrollment failed for training {}", trainingId, exception);
            throw exception;
        }
    }

    public int getParticipantsCount(int trainingId) {
        Training training = trainingRepository.findById(trainingId);
        if (training == null) {
            throw new IllegalArgumentException("Тренировка не найдена");
        }
        return training.getParticipantsCount();
    }
}
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
