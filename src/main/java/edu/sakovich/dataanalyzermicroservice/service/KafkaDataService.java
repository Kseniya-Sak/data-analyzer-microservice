package edu.sakovich.dataanalyzermicroservice.service;

// Обрабатывает прочитанные данные

import edu.sakovich.dataanalyzermicroservice.model.Data;

public interface KafkaDataService {

    void hand(Data data);
}
