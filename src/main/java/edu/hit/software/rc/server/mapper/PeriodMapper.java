package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Period;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Mapper
public interface PeriodMapper {
    Period getPeriodById(long id);
    long insertPeriod(Timestamp begin, Timestamp end, String pos, long memberGroup, String tag, boolean canceled);
    List<Long> selectPeriodsSince(Timestamp since, long memberGroup);
    void cancelPeriod(long id);
}
