package edu.sakovich.dataanalyzermicroservice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sakovich.dataanalyzermicroservice.config.LocalDateTimeDeserializer;
import edu.sakovich.dataanalyzermicroservice.model.Data;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaDataReceiverImpl implements KafkaDataReceiver {

    private final KafkaReceiver<String, Object> receiver;
    private final LocalDateTimeDeserializer  localDateTimeDeserializer;
    private final KafkaDataService kafkaDataService;

    // fetch запускается, когда мы запускаем приложение
    @PostConstruct
    private void init() {
        fetch();
    }

    @Override
    public void fetch() {
//        Регистрируем как обрабатывать LocalDateTime
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        localDateTimeDeserializer)
                .create();
        receiver.receive()
                .subscribe(r -> {
                    Data data = gson.fromJson(r.value().toString(), Data.class);
                    kafkaDataService.hand(data);
//                    говорим кафке, что мы получили сообщение, обработали и ждем следующее сообщение
                    r.receiverOffset().acknowledge();
                        });

    }
}
