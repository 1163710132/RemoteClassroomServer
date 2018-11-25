package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.GroupEntry;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupEntryMapper {
    @Select("SELECT COUNT(*) FROM REMOTE_CLASSROOM.GROUP_ENTRY WHERE MEMBER = #{member} AND MEMBER_GROUP = #{group}")
    int countEntriesByMAG(@Param("member") long member, @Param("group") long group);

    @Select("SELECT * FROM REMOTE_CLASSROOM.GROUP_ENTRY WHERE MEMBER = #{member} AND COURSE = #{course}")
    List<GroupEntry> getEntriesByMAC(@Param("member") long member, @Param("course") long course);

    @Select("SELECT * FROM REMOTE_CLASSROOM.GROUP_ENTRY WHERE COURSE = #{course}")
    List<GroupEntry> getEntriesByCourse(@Param("course") long course);

    @Select("SELECT COUNT(*) FROM REMOTE_CLASSROOM.GROUP_ENTRY WHERE MEMBER = #{member} AND COURSE = #{course}")
    int countEntriesByMAC(@Param("member") long member, @Param("course") long course);

    @Select("SELECT * FROM REMOTE_CLASSROOM.GROUP_ENTRY WHERE MEMBER = #{member}")
    List<GroupEntry> getEntriesByMember(@Param("member") long member);

    @Select("SELECT * FROM REMOTE_CLASSROOM.GROUP_ENTRY WHERE MEMBER_GROUP = #{memberGroup}")
    List<GroupEntry> getEntriesByMemberGroup(@Param("memberGroup") long memberGroup);
    @Insert("INSERT INTO REMOTE_CLASSROOM.GROUP_ENTRY(MEMBER, MEMBER_GROUP, COURSE) " +
            "VALUES (#{member}, #{memberGroup}, #{course})")
    int insertEntry(GroupEntry groupEntry);

    @Delete("DELETE FROM REMOTE_CLASSROOM.GROUP_ENTRY WHERE MEMBER = #{member} AND MEMBER_GROUP = #{memberGroup}")
    int deleteEntry(@Param("member") long member, @Param("memberGroup") long memberGroup);
}
