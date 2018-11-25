package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface ScoreMapper {
    Score getScore(long user, long memberGroup, String name);
    void insertScore(long user, long memberGroup, String name, BigDecimal value);
    List<Score> getScoresByGroup(long user, long memberGroup);
}
