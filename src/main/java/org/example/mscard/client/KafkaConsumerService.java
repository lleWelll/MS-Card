package org.example.mscard.client;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.mscard.dto.CardOperationInKafkaDTO;
import org.example.mscard.entity.CardOperationInKafkaEntity;
import org.example.mscard.mapper.CardOperationMapper;
import org.example.mscard.repository.CardOperationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {
    private final CardOperationRepository cardOperationRepository;
    private final CardOperationMapper cardOperationMapper;

    @KafkaListener(topics = "bank-card-topic", groupId = "bank-card-group")
    public void listen(ConsumerRecord<String, CardOperationInKafkaDTO> record) {
        CardOperationInKafkaDTO cardOperationInKafkaDTO = record.value();
        log.info("Received message: {}", cardOperationInKafkaDTO);
        CardOperationInKafkaEntity cardOperationInKafkaEntity = cardOperationMapper.toEntity(cardOperationInKafkaDTO);
        cardOperationRepository.save(cardOperationInKafkaEntity);
    }

}

