package com.callog.callogbackendalim.event.consumer;

import com.callog.callogbackendalim.event.consumer.message.diet.DietCompleteEvent;
import com.callog.callogbackendalim.event.consumer.message.user.SiteUserInfoEvent;
import com.callog.callogbackendalim.service.MailAlimService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageConsumer {
    private final MailAlimService mailAlimService;
//    @KafkaListener(
//            topics = SiteUserInfoEvent.Topic,
//            properties = {
//                    JsonDeserializer.VALUE_DEFAULT_TYPE + ":com.welab.backend_alim.event.consumer.message.user.SiteUserInfoEvent"
//            }
//    )
//    void handleSiteUserInfoEvent(SiteUserInfoEvent event, Acknowledgment ack) {
//        log.info("SiteUserInfoEvent 처리. userId={}", event.getUserId());
//
//        ack.acknowledge();
//    }

    @KafkaListener(
            topics = DietCompleteEvent.Topic,
            properties = {
                    JsonDeserializer.VALUE_DEFAULT_TYPE + ":com.callog.callogbackendalim.event.consumer.message.diet.DietCompleteEvent"
            }
    )
    void handlePostCommentEvent(DietCompleteEvent event, Acknowledgment ack) throws MessagingException {
        log.info("DietCompleteEvent 처리 시작");
        try{
            log.info("DietCompleteEvent 처리. userId={}", event.getUserId());
            mailAlimService.sendEmail(event.getUserId(), event.getSubject(),event.getNickName(), event.getMessage());
            ack.acknowledge();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
