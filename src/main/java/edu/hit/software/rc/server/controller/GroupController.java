package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.MemberGroup;
import edu.hit.software.rc.server.service.CourseService;
import edu.hit.software.rc.server.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/group")
public class GroupController implements Controller {
    private final GroupService groupService;
    private final CourseService courseService;

    @Autowired
    public GroupController(GroupService groupService, CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @RequestMapping("/periods")
    public List periods(int group, int since, int until){
        return List.of();
    }
    @RequestMapping("/members")
    public List members(long group){
        long course = groupService.getCourse(group);
        return groupService.members(group);
    }
    @RequestMapping("/join")
    public boolean join(int group, int user){
        return groupService.addMember(user, group);
    }
    @RequestMapping("/create")
    public long create(String name, long course){
        long manager = courseService.getCourse(course).getManager();
        MemberGroup group = groupService.createGroup(name, course);
        return group.getId();
    }
    @RequestMapping("/secede")
    public boolean secede(int group, int user){
        long course = groupService.getCourse(group);
        long manager = courseService.getCourse(course).getManager();
        return groupService.removeMember(user, group);
    }
}
