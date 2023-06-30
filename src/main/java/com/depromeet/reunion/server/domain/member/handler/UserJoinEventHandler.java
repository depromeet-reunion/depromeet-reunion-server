package com.depromeet.reunion.server.domain.member.handler;

import com.depromeet.reunion.server.domain.member.event.UserJoinEvent;
import com.depromeet.reunion.server.infra.slack.SlackService;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.TextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ButtonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserJoinEventHandler {

    private final SlackService slackService;

    @Value(value = "${server.url}")
    private String serverUrl;

    @Value(value = "${slack.join-alert-channel}")
    private String slackJoinAlertChannel;

    @EventListener
    @Async
    public void sendEvent(UserJoinEvent event) {
        String title = "신규 유저가 가입하였습니다.";

        List<TextObject> textObjectList = new ArrayList<>();
        textObjectList.add(markdownText("*유저 이름:* " + event.getMember().getName()));
        textObjectList.add(markdownText("*유저 핸드폰 번호:* " + event.getMember().getMemberAuth().getPhoneNumber()));
        textObjectList.add(markdownText("*유저 파트:* " + event.getMember().getMemberGroup().getPart()));
        textObjectList.add(markdownText("*유저 기수:* " + event.getMember().getMemberGroup().getUnit()));

        List<BlockElement> blockElementList = new ArrayList<>();

        List<LayoutBlock> blocks = asBlocks(
                header(h -> h.text(plainText(title))),
                section(section -> section.fields(textObjectList)),
                actions(a -> a.elements(blockElementList))
        );

        slackService.sendMessage(blocks, slackJoinAlertChannel);
    }

}
