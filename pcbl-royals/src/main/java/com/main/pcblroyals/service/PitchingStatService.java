package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.bean.PitchingStatPlayerBean;
import com.main.pcblroyals.bean.PitchingStatSeasonBean;
import com.main.pcblroyals.dao.PitchingStatDao;
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
    public List<PitchingStatSeasonBean> getPitchingStatsSeasonByPlayer(int playerId){
        return pitchingStatDao.getPitchingStatsSeasonByPlayer(playerId);
    }
}
