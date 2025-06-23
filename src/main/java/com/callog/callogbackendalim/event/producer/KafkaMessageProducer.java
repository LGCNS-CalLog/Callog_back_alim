package com.callog.callogbackendalim.event.producer;

import com.callog.callogbackendalim.event.consumer.message.diet.DietCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendDietCompleteEvent(DietCompleteEvent event) {
        log.info("🟦 Kafka 메시지 전송 시작: topic={}, payload={}", DietCompleteEvent.Topic, event);
        kafkaTemplate.send(DietCompleteEvent.Topic, event);
        log.info("✅ Kafka 메시지 전송 완료: {}", event.getUserId());
    }
}
