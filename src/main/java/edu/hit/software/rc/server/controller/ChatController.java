package edu.hit.software.rc.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/chat")
public class ChatController implements Controller {
    @RequestMapping("/send")
    public int send(int group, String content){
        return 0;
    }
    @RequestMapping("/pull")
    public List pull(int group, int since){
        return List.of();
    }
}
