package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.*;
import com.main.pcblroyals.dao.PitchingStatDao;
import com.main.pcblroyals.data.PitchingStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 10/11/18.
 */
@Service("pitchingStatService")
public class PitchingStatService {

    @Autowired
    @Qualifier("pitchingStatDao")
    private PitchingStatDao pitchingStatDao;

    @Transactional
    public List<PitchingStatPlayerBean> getPitchingStatsBySeason(int seasonId){
        return pitchingStatDao.getPitchingStatsBySeason(seasonId);
    }

    @Transactional
    public List<PitchingStatPlayerBean> getPitchingStatsBySeasonGame(int seasonId, int gameId){
        return pitchingStatDao.getPitchingStatsBySeasonGame(seasonId, gameId);
    }

    @Transactional
    public List<PitchingStatPlayerBean> getPitchingStatForSeasonGamePlayer(int seasonId, int gameId, int playerId){
        return pitchingStatDao.getPitchingStatForSeasonGamePlayer(seasonId, gameId, playerId);
    }

    @Transactional
    public List<PitchingStatSeasonBean> getPitchingStatsSeasonByPlayer(int playerId){
        return pitchingStatDao.getPitchingStatsSeasonByPlayer(playerId);
    }

    @Transactional
    public List<PitchingStatGameBean> getPitchingStatsGameBySeasonPlayer(int seasonId, int playerId){
        return pitchingStatDao.getPitchingStatsGameBySeasonPlayer(seasonId,playerId);
    }

    @Transactional
    public List<PitchingStatBean> getAllTimePitchingStatForSeason(int seasonId){
        return pitchingStatDao.getAllTimePitchingStatForSeason(seasonId);
    }

    public List<PitchingStatBean> getTeamPitchingStatsForGame(int seasonId, int playerId){
        return pitchingStatDao.getTeamPitchingStatsForGame(seasonId,playerId);
    }

    public List<PitchingStatBean> getAllTimePitchingStatsForPlayer(int playerId){
        return pitchingStatDao.getAllTimePitchingStatsForPlayer(playerId);
    }

    public List<PitchingStatBean> getTotalPitchingStatsForPlayerBySeason(int seasonId,int playerId){
        return pitchingStatDao.getTotalPitchingStatsForPlayerBySeason(seasonId,playerId);
    }

    @Transactional
    public void addPitchingStat(PitchingStat pitchingStat){ pitchingStatDao.insertPitchingStat(pitchingStat);}
}
