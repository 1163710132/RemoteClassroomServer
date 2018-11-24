package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.service.HomeworkService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/question")
public class QuestionController implements Controller {
    private final HomeworkService homeworkService;

    public QuestionController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @RequestMapping("/pull")
    public String pull(long question){
        return homeworkService.getQuestion(question).getContent();
    }
    @RequestMapping("/create")
    public long create(String content){
        if(getSubject().isAuthenticated()){
            return homeworkService.createQuestion(getAccount().getId(), content).getId();
        }else{
            return -1;
        }
    }
}
