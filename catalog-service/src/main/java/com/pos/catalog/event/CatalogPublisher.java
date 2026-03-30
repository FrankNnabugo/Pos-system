package com.pos.catalog.event;

import com.pos.catalog.common.dto.CreateCatalogRequest;
import com.pos.catalog.common.dto.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CatalogPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${spring.kafka.topics.catalog}")
    private String catalog;

    public void publishProductCreatedEvent(String catalogId, CreateCatalogRequest request){
        ProductCreatedEvent event = new ProductCreatedEvent(
                UUID.randomUUID().toString(),
                catalogId,
                request,
                Instant.now()
        );

        kafkaTemplate.send(catalog, event.getCatalogId(), event)
                .whenComplete((metaData, throwable)->{
                    if(throwable != null){
                        log.error("error publishing product created event", throwable);
                        throw new RuntimeException(throwable);
                    }
                    log.info("product event ({}) published to {} - {}",
                            metaData.getProducerRecord().value(),
                            metaData.getRecordMetadata().topic(),
                            metaData.getRecordMetadata().partition());
                });
    }
}
