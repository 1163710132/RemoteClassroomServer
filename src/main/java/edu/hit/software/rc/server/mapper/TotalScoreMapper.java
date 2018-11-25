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
    TotalScore getTotalScore(long course, long student);
    List<TotalScore> getTotalScoresByCourse(long course);
    List<TotalScore> getTotalScoresByAccount(long account);
    long insertTotalScore(long course, long student, long method, BigDecimal score);
}
