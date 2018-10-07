package com.main.pcblroyals.service;

import com.main.pcblroyals.dao.BattingStatDao;
import com.main.pcblroyals.data.BattingStat;
import com.main.pcblroyals.data.SeasonBattingStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("battingStatService")
public class BattingStatService {

    @Autowired
    @Qualifier("battingStatDao")
    private BattingStatDao battingStatDao;

    @Transactional
    public List<SeasonBattingStat> getSeasonStats(int seasonId){
        List<BattingStat> battingStats =  battingStatDao.getSeasonStats(seasonId);
        return combineBattingStatsToSeason(battingStats);
    }

    private List<SeasonBattingStat> combineBattingStatsToSeason(List<BattingStat> battingStats){

        for(BattingStat currStat : battingStats){

        }
        SeasonBattingStat sbs = new SeasonBattingStat();

        return new ArrayList<SeasonBattingStat>();
    }
}
