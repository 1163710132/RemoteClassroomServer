package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Account;
import edu.hit.software.rc.server.entity.Course;
import edu.hit.software.rc.server.permission.Permissions;
import edu.hit.software.rc.server.service.AccountService;
import edu.hit.software.rc.server.service.CourseService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/course")
public class CourseController implements Controller {
    private final AccountService accountService;
    private final CourseService courseService;

    @Autowired
    public CourseController(AccountService accountService, CourseService courseService) {
        this.accountService = accountService;
        this.courseService = courseService;
    }

    @RequestMapping("/info")
    public String info(){
        return "";
    }
    @RequestMapping("/members")
    public List members(long course){
        if(getSubject().isPermitted(Permissions.ofCourse(course))){
            return courseService.getMembers(course);
        }else{
            return List.of();
        }
    }
    @RequestMapping("/groups")
    public List groups(long course){
        if(getSubject().isPermitted(Permissions.ofCourse(course))){
            return courseService.getGroups(course);
        }else{
            return List.of();
        }
    }
    @RequestMapping(value = "/create")
    public long create(String name){
        Subject subject = getSubject();
        if(subject.isAuthenticated()){
            Account account = (Account) subject.getPrincipal();
            Course course = courseService.createCourse(name, account.getId());
            return course.getId();
        }else{
            return -1;
        }
    }
    @RequestMapping("/cancel")
    public boolean cancel(int course){
        long manager = courseService.getCourse(course).getManager();
        if(getSubject().isPermitted(Permissions.ofGroup(manager))){
            return courseService.cancelCourse(course);
        }else{
            return false;
        }
    }
}
