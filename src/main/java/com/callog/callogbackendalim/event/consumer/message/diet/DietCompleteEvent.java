package com.callog.callogbackendalim.event.consumer.message.diet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietCompleteEvent {
    public static final String Topic = "diet-complete";

    private String action;

    private String userId; // email 양식
    private String nickName;
    private String subject; // 메일 제목
    private String message;  // 메일 메세지
}
