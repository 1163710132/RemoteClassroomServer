package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.mapper.CourseMapper;
import edu.hit.software.rc.server.mapper.GroupEntryMapper;
import edu.hit.software.rc.server.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    private final GroupEntryMapper groupEntryMapper;

    @Autowired
    public PermissionService(GroupEntryMapper groupEntryMapper) {
        this.groupEntryMapper = groupEntryMapper;
    }

    public boolean accessGroup(long uid, long groupId){
        return groupEntryMapper.countEntriesByMAG(uid, groupId) > 0;
    }

    public boolean accessCourse(long uid, long courseId){
        return groupEntryMapper.countEntriesByMAC(uid, courseId) > 0;
    }
}
