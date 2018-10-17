package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.BattingStatBean;
import com.main.pcblroyals.bean.BattingStatGameBean;
import com.main.pcblroyals.bean.BattingStatPlayerBean;
import com.main.pcblroyals.bean.BattingStatSeasonBean;
import com.main.pcblroyals.dao.BattingStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("battingStatService")
public class BattingStatService {

    @Autowired
    @Qualifier("battingStatDao")
    private BattingStatDao battingStatDao;

//    @Transactional
//    public List<BattingStat> getBattingStats(){
//        return battingStatDao.getBattingStats();
//    }

    @Transactional
    public List<BattingStatPlayerBean> getBattingStatsBySeason(int seasonId){
        return battingStatDao.getBattingStatsBySeason(seasonId);
    }

    @Transactional
    public List<BattingStatPlayerBean> getBattingStatsBySeasonGame(int seasonId, int gameId){
        return battingStatDao.getBattingStatsBySeasonGame(seasonId, gameId);
    }

    @Transactional
    public List<BattingStatSeasonBean> getBattingStatsSeasonByPlayer(int playerId){
        return battingStatDao.getBattingStatsSeasonByPlayer(playerId);
    }

    @Transactional
    public List<BattingStatGameBean> getBattingStatsGameBySeasonPlayer(int seasonId, int playerId){
        return battingStatDao.getBattingStatsGameBySeasonPlayer(seasonId,playerId);
    }
}
