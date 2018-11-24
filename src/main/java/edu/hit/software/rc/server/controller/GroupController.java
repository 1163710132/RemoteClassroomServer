package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.MemberGroup;
import edu.hit.software.rc.server.permission.Permissions;
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
        if(getSubject().isPermitted(Permissions.ofCourse(course))){
            return groupService.members(group);
        }else{
            return List.of();
        }
    }
    @RequestMapping("/join")
    public boolean join(int group, int user){
        if(getSubject().isPermitted(Permissions.ofGroup(group))){
            return groupService.addMember(user, group);
        }else{
            return false;
        }
    }
    @RequestMapping("/create")
    public long create(String name, long course){
        long manager = courseService.getCourse(course).getManager();
        if(getSubject().isPermitted(Permissions.ofGroup(manager))){
            MemberGroup group = groupService.createGroup(name, course);
            return group.getId();
        }else{
            return -1;
        }
    }
    @RequestMapping("/secede")
    public boolean secede(int group, int user){
        long course = groupService.getCourse(group);
        long manager = courseService.getCourse(course).getManager();
        if(getSubject().isPermitted(Permissions.ofGroup(manager))){
            return groupService.removeMember(user, group);
        }else{
            return false;
        }
    }
}
