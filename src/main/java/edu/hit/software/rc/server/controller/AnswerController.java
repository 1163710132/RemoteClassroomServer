package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.permission.Permissions;
import edu.hit.software.rc.server.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@EnableAutoConfiguration
@RequestMapping("/answer")
public class AnswerController implements Controller {
    private final HomeworkService homeworkService;

    @Autowired
    public AnswerController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @RequestMapping("/commit")
    public long commit(long question, long publisher, String answer){
        if(getSubject().isPermitted(Permissions.ofAccount(publisher))){
            return homeworkService.createAnswer(publisher, question, answer).getId();
        }else{
            return -1;
        }
    }
}
