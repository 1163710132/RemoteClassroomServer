package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.TotalScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface TotalScoreMapper {
    TotalScore getTotalScoreById(long id);
    Long selectTotalScore(long course, long student);
    List<Long> selectTotalScores(long course);
    long insertTotalScore(long course, long student, long method, BigDecimal score);
}
