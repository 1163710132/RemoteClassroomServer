package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Account;
import edu.hit.software.rc.server.entity.Course;
import edu.hit.software.rc.server.entity.GroupEntry;
import edu.hit.software.rc.server.service.AccountService;
import edu.hit.software.rc.server.service.CourseService;
import edu.hit.software.rc.server.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@RequestMapping("/course")
public class CourseController implements Controller {
    private final AccountService accountService;
    private final CourseService courseService;
    private final GroupService groupService;

    @Autowired
    public CourseController(AccountService accountService, CourseService courseService, GroupService groupService) {
        this.accountService = accountService;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    @RequestMapping("/courses")
    public List<Course> courses(long uid, HttpSession session){
        if(requiresAccount(uid, session)){
            return groupService.groups(uid)
                    .stream()
                    .mapToLong(GroupEntry::getCourse)
                    .distinct()
                    .mapToObj(courseService::getCourse)
                    .collect(Collectors.toList());
        }else{
            return List.of();
        }
    }
    @RequestMapping("/members")
    public List members(long course){
        return courseService.getMembers(course);


    }
    @RequestMapping("/groups")
    public List groups(long course){
        return courseService.getGroups(course);
    }
    @RequestMapping(value = "/create")
    public long create(String name, HttpSession session){
        Course course = courseService.createCourse(name, getUid(session));
        return course.getId();
    }
    @RequestMapping("/cancel")
    public boolean cancel(int course){
        return courseService.cancelCourse(course);
    }
}
