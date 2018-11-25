package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.Period;
import edu.hit.software.rc.server.mapper.PeriodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PeriodService {
    private final PeriodMapper periodMapper;

    @Autowired
    public PeriodService(PeriodMapper periodMapper) {
        this.periodMapper = periodMapper;
    }

    public List<Period> getPeriodsByGroup(long group, Instant begin, Instant end){
        return periodMapper.getPeriodsByGroup(group, begin, end);
    }
}
