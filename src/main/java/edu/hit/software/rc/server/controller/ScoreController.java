package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Score;
import edu.hit.software.rc.server.entity.TotalScore;
import edu.hit.software.rc.server.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/score")
public class ScoreController implements Controller {
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping("/course/total")
    public TotalScore courseTotal(long user, long course){
        return scoreService.getTotalScore(user, course);
    }

    @RequestMapping("/all")
    public List<TotalScore> all(long user){
        return scoreService.getTotalScores(user);
    }

    @RequestMapping("/detail")
    public List<Score> detail(long score){
        return scoreService.getDetails(score);
    }

    @RequestMapping("/course/detail")
    public List<Score> courseDetail(long user, long course){
        return scoreService.getDetails(user, course);
    }
}
