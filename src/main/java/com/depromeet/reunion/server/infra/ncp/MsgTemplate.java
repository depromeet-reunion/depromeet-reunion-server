package com.depromeet.reunion.server.infra.ncp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class MsgTemplate implements Serializable {
    @JsonProperty("type")
    private final String type = "SMS";
    @JsonProperty("content")
    private final String content;
    @JsonProperty("messages")
    private final List<Message> messages;
    @JsonProperty("from")
    private String from;

    public MsgTemplate(String from, String content, String to) {
        this.content = content;
        this.from = from;
        this.messages = Collections.singletonList(new Message(to));
    }

    @AllArgsConstructor
    static class Message {
        @JsonProperty("to")
        private String to;
    }

}
