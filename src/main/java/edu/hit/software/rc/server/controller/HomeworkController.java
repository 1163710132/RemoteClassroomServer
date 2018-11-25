package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Course;
import edu.hit.software.rc.server.entity.Homework;
import edu.hit.software.rc.server.entity.HomeworkEntry;
import edu.hit.software.rc.server.entity.MemberGroup;
import edu.hit.software.rc.server.service.CourseService;
import edu.hit.software.rc.server.service.GroupService;
import edu.hit.software.rc.server.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    @RequestMapping("homework")
    public List<Homework> homework(long group){
        return homeworkService.getHomeworkByGroup(group);
    }


    @RequestMapping("/answer")
    public long answer(long homework, int qIndex, long publisher){
        HomeworkEntry entryEntity = homeworkService.getHomeworkEntry(homework, qIndex);
        Homework homeworkEntity = homeworkService.getHomework(homework);
        long question = entryEntity.getQuestion();
        long rater = homeworkEntity.getRater();
        return homeworkService.selectLastAnswer(publisher, question, homeworkEntity.getEnd());
    }

    @RequestMapping("/entries")
    public List<HomeworkEntry> countEntries(long homework){
        return homeworkService.getHomeworkEntries(homework);
    }

    @RequestMapping("/future")
    public List<Long> future(long group, Instant since){
        return homeworkService.selectFutureHomework(group, since);
    }

    @RequestMapping("/past")
    public List<Long> past(long group, Instant until){
        return homeworkService.selectPastHomework(group, until);
    }

    @RequestMapping("/now")
    public List<Long> now(long group, Instant now){
        return homeworkService.selectNowHomework(group, now);
    }

    @RequestMapping("/create")
    public long create(String name, long target, long rater, Instant begin, Instant end, long[] questions, HttpSession session){
        if(begin.isAfter(end)){
            return -1;
        }
        Homework homework = homeworkService.createHomework(
                name, getUid(session), target, rater, begin, end, questions);
        return homework.getId();
    }
    @RequestMapping("/cancel")
    public boolean cancel(int homework){
        Homework homeworkEntity = homeworkService.getHomework(homework);
        return homeworkService.cancelHomework(homework);
    }
}
