package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.Course;
import edu.hit.software.rc.server.entity.GroupEntry;
import edu.hit.software.rc.server.entity.MemberGroup;
import edu.hit.software.rc.server.mapper.CourseMapper;
import edu.hit.software.rc.server.mapper.GroupEntryMapper;
import edu.hit.software.rc.server.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseMapper courseMapper;
    private final GroupMapper groupMapper;
    private final GroupEntryMapper groupEntryMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper, GroupMapper groupMapper, GroupEntryMapper groupEntryMapper) {
        this.courseMapper = courseMapper;
        this.groupMapper = groupMapper;
        this.groupEntryMapper = groupEntryMapper;
    }

    public Course createCourse(String name, long creator){
        Course course = new Course();
        course.setName(name);
        course.setCreator(creator);
        if(courseMapper.insertCourse(course) > 0){
            MemberGroup memberGroup = new MemberGroup();
            memberGroup.setName(MemberGroup.MANAGER);
            memberGroup.setCourse(course.getId());
            if(groupMapper.insertGroup(memberGroup) > 0){
                courseMapper.updateManager(course.getId(), memberGroup.getId());
                GroupEntry groupEntry = new GroupEntry();
                groupEntry.setCourse(course.getId());
                groupEntry.setMember(creator);
                groupEntry.setMemberGroup(memberGroup.getId());
                if(groupEntryMapper.insertEntry(groupEntry) > 0){
                    return course;
                }
            }
        }
        return null;
    }

    public List<MemberGroup> getGroups(long courseId){
        return groupMapper.selectGroupsByCourse(courseId).stream()
                .map(groupMapper::getGroupById)
                .collect(Collectors.toList());
    }

    public List<Long> getMembers(long courseId){
        return groupEntryMapper.getEntriesByCourse(courseId).stream()
                .map(GroupEntry::getMember)
                .distinct()
                .collect(Collectors.toList());
    }

    public Course getCourse(long courseId){
        return courseMapper.getCourseById(courseId);
    }

    public boolean cancelCourse(long courseId){
        return courseMapper.cancelCourse(courseId) > 0;
    }
}
