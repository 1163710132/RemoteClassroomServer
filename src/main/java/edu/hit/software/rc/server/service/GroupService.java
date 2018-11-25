package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.GroupEntry;
import edu.hit.software.rc.server.entity.MemberGroup;
import edu.hit.software.rc.server.mapper.GroupEntryMapper;
import edu.hit.software.rc.server.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupEntryMapper groupEntryMapper;

    @Autowired
    public GroupService(GroupMapper groupMapper, GroupEntryMapper groupEntryMapper) {
        this.groupMapper = groupMapper;
        this.groupEntryMapper = groupEntryMapper;
    }

    public MemberGroup createGroup(String name, long courseId){
        MemberGroup memberGroup = new MemberGroup();
        memberGroup.setName(name);
        memberGroup.setCourse(courseId);
        if(groupMapper.insertGroup(memberGroup) > 0){
            return memberGroup;
        }
        return null;
    }

    public long getCourse(long groupId){
        return groupMapper.getGroupById(groupId).getCourse();
    }

    public MemberGroup getGroup(long groupId){
        return groupMapper.getGroupById(groupId);
    }

    public boolean addMember(long memberId, long groupId){
        MemberGroup group = getGroup(groupId);
        if(group != null){
            GroupEntry groupEntry = new GroupEntry();
            groupEntry.setMember(memberId);
            groupEntry.setMemberGroup(groupId);
            groupEntry.setCourse(group.getCourse());
            return groupEntryMapper.insertEntry(groupEntry) > 0;
        }
        return false;
    }

    public boolean removeMember(long memberId, long groupId){
        return groupEntryMapper.deleteEntry(memberId, groupId) > 0;
    }

    public List<GroupEntry> members(long groupId){
        return groupEntryMapper.getEntriesByMemberGroup(groupId);
    }

    public List<GroupEntry> groups(long accountId){
        return groupEntryMapper.getEntriesByMember(accountId);
    }
}
