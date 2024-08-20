package edu.sakovich.dataanalyzermicroservice.service;

import edu.sakovich.dataanalyzermicroservice.model.Data;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataServiceImpl implements KafkaDataService {

    @Override
    public void hand(Data data) {
        System.out.println("Data object is received: " + data.toString());
    }
}
