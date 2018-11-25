package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Homework;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
@Mapper
public interface HomeworkMapper {
    @Select("SELECT * FROM REMOTE_CLASSROOM.HOMEWORK WHERE ID = #{id}")
    Homework getHomeworkById(@Param("id") long id);

    @Insert("INSERT INTO REMOTE_CLASSROOM.HOMEWORK(PUBLISHER, TARGET_GROUP, PUBLISHED, BEGIN, END, CANCELED, NAME, RATER) " +
            "VALUES (#{publisher}, #{target}, #{published}, #{begin}, #{end}, #{canceled}, #{name}, #{rater})")
    int insertHomework(Homework homework);

    @Select("SELECT * FROM REMOTE_CLASSROOM.HOMEWORK WHERE TARGET_GROUP = #{targetGroup}")
    List<Homework> getHomeworkByTargetGroup(@Param("targetGroup") long targetGroup);

    @Update("UPDATE REMOTE_CLASSROOM.HOMEWORK SET CANCELED = TRUE WHERE ID = #{id}")
    int cancelHomework(@Param("id") long id);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.HOMEWORK " +
            "WHERE TARGET_GROUP = #{targetGroup} AND BEGIN < #{since}")
    List<Long> selectFutureHomework(@Param("targetGroup") long targetGroup, @Param("since") Instant since);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.HOMEWORK " +
            "WHERE TARGET_GROUP = #{targetGroup} AND END > #{until}")
    List<Long> selectPastHomework(@Param("targetGroup") long targetGroup, @Param("until") Instant until);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.HOMEWORK " +
            "WHERE TARGET_GROUP = #{targetGroup} AND BEGIN > #{now} AND END < #{now}")
    List<Long> selectNowHomework(@Param("targetGroup") long targetGroup, @Param("now") Instant now);
}
