package edu.hit.software.rc.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/score")
public class ScoreController implements Controller {
    @RequestMapping("pull")
    public List pull(int course, int user){
        return List.of();
    }
    @RequestMapping("commit")
    public boolean commit(int course, int user, String score, String tag){
        return true;
    }
}
