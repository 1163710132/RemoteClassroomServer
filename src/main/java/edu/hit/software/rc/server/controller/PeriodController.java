package edu.hit.software.rc.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/period")
public class PeriodController implements Controller {
    @RequestMapping("/period")
    public String period(int id){
        return "";
    }
    @RequestMapping("/periods")
    public List periods(int[] ids){
        return List.of();
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
