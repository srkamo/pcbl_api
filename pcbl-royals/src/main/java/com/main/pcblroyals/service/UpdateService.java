package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SeasonPlayerBean;
import com.main.pcblroyals.bean.SeasonTeamBean;
import com.main.pcblroyals.dao.UpdateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 11/11/18.
 */
@Service("updateService")
public class UpdateService {
    @Autowired
    @Qualifier("updateDao")
    UpdateDao updateDao;

    @Transactional
    public void addSeasonPlayerIfNotExists(SeasonPlayerBean seasonPlayerBean){
        int season_id = seasonPlayerBean.getSeason_id();
        List<Integer> player_ids = seasonPlayerBean.getPlayer_ids();
        for(int player_id:player_ids){
            updateDao.addSeasonPlayerIfNotExists(season_id,player_id);
        }
    }

    @Transactional
    public void addSeasonTeamIfNotExists(SeasonTeamBean seasonTeamBean){
        int season_id = seasonTeamBean.getSeason_id();
        List<Integer> team_ids = seasonTeamBean.getTeam_ids();
        for(int team_id:team_ids){
            updateDao.addSeasonTeamIfNotExists(season_id,team_id);
        }
    }

}
