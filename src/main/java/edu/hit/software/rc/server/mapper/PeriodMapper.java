package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Period;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
@Mapper
public interface PeriodMapper {
    @Select("SELECT * FROM PERIOD WHERE ID = #{id}")
    Period getPeriodById(@Param("id") long id);
    long insertPeriod(Timestamp begin, Timestamp end, String pos, long memberGroup, String tag, boolean canceled);
    @Insert("SELECT * FROM REMOTE_CLASSROOM.PERIOD " +
            "WHERE MEMBER_GROUP = #{memberGroup} AND " +
            "(BEGIN BETWEEN #{begin} AND #{end} " +
            "OR END BETWEEN #{begin} AND #{end} " +
            "OR (BEGIN < #{begin} AND END > #{end}))")
    List<Period> getPeriodsByGroup(@Param("memberGroup") long memberGroup, @Param("begin") Instant begin, @Param("end") Instant end);
    @Delete("DELETE FROM REMOTE_CLASSROOM.PERIOD WHERE ID = #{id}")
    void cancelPeriod(@Param("id") long id);
}
