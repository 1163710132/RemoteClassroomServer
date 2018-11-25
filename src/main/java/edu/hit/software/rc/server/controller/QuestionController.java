package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Question;
import edu.hit.software.rc.server.service.HomeworkService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@RestController
@EnableAutoConfiguration
@RequestMapping("/question")
public class QuestionController implements Controller {
    private final HomeworkService homeworkService;

    public QuestionController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @RequestMapping("")
    public String pull(long question){
        return homeworkService.getQuestion(question).getContent();
    }

    @RequestMapping("/create")
    public Question create(String content , HttpSession session){
        return homeworkService.createQuestion(getUid(session), content);
    }
}
