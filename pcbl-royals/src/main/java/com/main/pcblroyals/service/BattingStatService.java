package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.*;
import com.main.pcblroyals.dao.BattingStatDao;
import com.main.pcblroyals.data.BattingStat;
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
    public List<BattingStat> getBattingStatForSeasonGamePlayer(int seasonId, int gameId, int playerId){
        return battingStatDao.getBattingStatForSeasonGamePlayer(seasonId, gameId, playerId);
    }

    @Transactional
    public List<BattingStatSeasonBean> getBattingStatsSeasonByPlayer(int playerId){
        return battingStatDao.getBattingStatsSeasonByPlayer(playerId);
    }

    @Transactional
    public List<BattingStatGameBean> getBattingStatsGameBySeasonPlayer(int seasonId, int playerId){
        return battingStatDao.getBattingStatsGameBySeasonPlayer(seasonId,playerId);
    }

    @Transactional
    public List<BattingStatBean> getAllTimeBattingStatForSeason(int seasonId){
        return battingStatDao.getAllTimeBattingStatForSeason(seasonId);
    }

    @Transactional
    public List<BattingStatBean> getTeamStatsForGame(int seasonId, int gameId){
        return battingStatDao.getTeamStatsForGame(seasonId, gameId);
    }

    public List<BattingStatBean> getAllTimeBattingStatsForPlayer(int playerId){
        return battingStatDao.getAllTimeBattingStatsForPlayer(playerId);
    }

    public List<BattingStatBean> getTotalBattingStatsForByPlayerBySeason(int seasonId, int playerId){
        return battingStatDao.getTotalBattingStatsForPlayerBySeason(seasonId,playerId);
    }

    @Transactional
    public void addBattingStat(BattingStat battingStat){battingStatDao.insertBattingStat(battingStat);}


}
