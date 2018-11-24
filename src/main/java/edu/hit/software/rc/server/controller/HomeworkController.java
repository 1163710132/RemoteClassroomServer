package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Course;
import edu.hit.software.rc.server.entity.Homework;
import edu.hit.software.rc.server.entity.HomeworkEntry;
import edu.hit.software.rc.server.entity.MemberGroup;
import edu.hit.software.rc.server.permission.Permissions;
import edu.hit.software.rc.server.service.CourseService;
import edu.hit.software.rc.server.service.GroupService;
import edu.hit.software.rc.server.service.HomeworkService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/homework")
public class HomeworkController implements Controller {
    private final HomeworkService homeworkService;
    private final GroupService groupService;
    private final CourseService courseService;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, GroupService groupService, CourseService courseService) {
        this.homeworkService = homeworkService;
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @RequestMapping("/answer")
    public long answer(long homework, int qIndex, long publisher){
        HomeworkEntry entryEntity = homeworkService.getHomeworkEntry(homework, qIndex);
        Homework homeworkEntity = homeworkService.getHomework(homework);
        long question = entryEntity.getQuestion();
        long rater = homeworkEntity.getRater();
        if(requiresGroup(rater) || requiresAccount(publisher)){
            return homeworkService.selectLastAnswer(publisher, question, homeworkEntity.getEnd());
        }else{
            return -1;
        }
    }

    @RequestMapping("/countEntries")
    public int countEntries(long homework){
        Homework homeworkEntity = homeworkService.getHomework(homework);
        if(getSubject().isPermitted(Permissions.ofGroup(homeworkEntity.getTarget()))){
            return homeworkService.selectHomeworkEntries(homework).size();
        }else{
            return -1;
        }
    }

    @RequestMapping("/future")
    public List<Long> future(long group, Instant since){
        if(getSubject().isPermitted(Permissions.ofGroup(group))){
            return homeworkService.selectFutureHomework(group, since);
        }else{
            return List.of();
        }
    }

    @RequestMapping("/past")
    public List<Long> past(long group, Instant until){
        if(getSubject().isPermitted(Permissions.ofGroup(group))){
            return homeworkService.selectPastHomework(group, until);
        }else{
            return List.of();
        }
    }

    @RequestMapping("/now")
    public List<Long> now(long group, Instant now){
        if(getSubject().isPermitted(Permissions.ofGroup(group))){
            return homeworkService.selectNowHomework(group, now);
        }else{
            return List.of();
        }
    }

    @RequestMapping("/create")
    public long create(String name, long target, long rater, Instant begin, Instant end, long[] questions){
        if(begin.isAfter(end)){
            return -1;
        }
        long course = groupService.getCourse(target);
        long manager = courseService.getCourse(course).getManager();
        if(getSubject().isPermitted(Permissions.ofGroup(manager))){
            Homework homework = homeworkService.createHomework(
                    name, getAccount().getId(), target, rater, begin, end, questions);
            return homework.getId();
        }else{
            return -1;
        }
    }
    @RequestMapping("/cancel")
    public boolean cancel(int homework){
        Homework homeworkEntity = homeworkService.getHomework(homework);
        if(getSubject().isPermitted(Permissions.ofAccount(homeworkEntity.getPublisher()))){
            return homeworkService.cancelHomework(homework);
        }else{
            return false;
        }
    }
}
