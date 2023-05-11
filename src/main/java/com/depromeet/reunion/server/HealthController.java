package com.depromeet.reunion.server;

import com.depromeet.reunion.server.domain.member.model.Member;
import com.depromeet.reunion.server.global.annotation.ReqMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthController {
    @RequestMapping(value = {"/", "/health"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String health() {
        return "Health Good";
    }

    @RequestMapping(value = {"/user-name"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String user(@ReqMember Member member) {
        return member.getName();
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String test() {
        return "test";
    }

}
