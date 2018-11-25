package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Period;
import edu.hit.software.rc.server.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/period")
public class PeriodController implements Controller {
    private final PeriodService periodService;

    @Autowired
    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @RequestMapping("/period")
    public String period(int id){
        return "";
    }
    @RequestMapping("")
    public List<Period> periods(long group, Instant begin, Instant end){
        return periodService.getPeriodsByGroup(group, begin, end);
    }
    @RequestMapping("/create")
    public int create(int group, int start, int end, String pos, String tag){
        return 0;
    }
    @RequestMapping("/cancel")
    public boolean cancel(int id){
        return true;
    }
}
