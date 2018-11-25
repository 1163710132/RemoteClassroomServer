package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.Score;
import edu.hit.software.rc.server.entity.TotalScore;
import edu.hit.software.rc.server.mapper.GroupMapper;
import edu.hit.software.rc.server.mapper.ScoreMapper;
import edu.hit.software.rc.server.mapper.TotalScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {
    private final ScoreMapper scoreMapper;
    private final TotalScoreMapper totalScoreMapper;
    private final GroupMapper groupMapper;

    @Autowired
    public ScoreService(ScoreMapper scoreMapper, TotalScoreMapper totalScoreMapper, GroupMapper groupMapper) {
        this.scoreMapper = scoreMapper;
        this.totalScoreMapper = totalScoreMapper;
        this.groupMapper = groupMapper;
    }

    public TotalScore getTotalScore(long accountId, long courseId){
        return totalScoreMapper.getTotalScore(courseId, accountId);
    }

    public List<TotalScore> getTotalScores(long accountId){
        return totalScoreMapper.getTotalScoresByAccount(accountId);
    }

    public List<Score> getDetails(long totalScoreId){
        throw new UnsupportedOperationException();
    }

    public List<Score> getDetails(long accountId, long courseId){
        return groupMapper.selectGroupsByCourse(courseId).stream()
                .flatMap(groupId -> scoreMapper.getScoresByGroup(accountId, groupId).stream())
                .collect(Collectors.toList());
    }
}
