package com.depromeet.reunion.server;

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

}
