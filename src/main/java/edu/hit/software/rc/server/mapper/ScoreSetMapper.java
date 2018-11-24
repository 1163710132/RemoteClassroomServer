package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.ScoreSet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ScoreSetMapper {
    ScoreSet getScoreSetById(long id);
    List<Long> listScoreSets();
    List<Long> selectScoreSetsByCourse(long course);
    long insertScoreSet(long course, String expr);
}
