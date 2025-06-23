package com.callog.callogbackendalim.api.open;


import com.callog.callogbackendalim.event.consumer.message.diet.DietCompleteEvent;
import com.callog.callogbackendalim.event.producer.KafkaMessageProducer;
import com.callog.callogbackendalim.remote.user.RemoteUserService;
import com.callog.callogbackendalim.remote.user.dto.UserInfoDto;
import com.callog.callogbackendalim.service.MailAlimService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/alim/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AlimController {
    private final RemoteUserService remoteUserService;
    private final MailAlimService mailAlimService;
    private final KafkaMessageProducer kafkaMessageProducer;

    @GetMapping("/user/info")
    public UserInfoDto.Response userInfo(@RequestParam String userId) {
        return remoteUserService.userInfo(userId);
    }

    @GetMapping("/mail/test")
    public String testMail() throws MessagingException {
//        mailAlimService.sendEmail("bravadosw@naver.com", "테스트메일", "테스트닉네임","테스트메일전송입니다");
        DietCompleteEvent event = new DietCompleteEvent();
        event.setAction("DIET_COMPLETE");
        event.setUserId("bravadosw@naver.com");
        event.setNickName("브라보");
        event.setSubject("오늘 식단 완료!");
        event.setMessage("오늘 아침, 점심, 저녁 식단이 모두 등록되었습니다. 수고하셨습니다!");
        kafkaMessageProducer.sendDietCompleteEvent(event);
        return "메일 전송 테스트가 성공적으로 완료되었습니다";
    }
}
