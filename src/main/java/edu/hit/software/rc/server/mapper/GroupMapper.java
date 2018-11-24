package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.MemberGroup;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupMapper {
    @Select("SELECT * FROM REMOTE_CLASSROOM.MEMBER_GROUP WHERE ID = #{id}")
    MemberGroup getGroupById(@Param("id") long id);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.MEMBER_GROUP WHERE COURSE = #{course}")
    List<Long> selectGroupsByCourse(@Param("course") long course);

    @Insert("INSERT INTO REMOTE_CLASSROOM.MEMBER_GROUP(COURSE, NAME) " +
            "VALUES (#{course}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertGroup(MemberGroup memberGroup);
}
