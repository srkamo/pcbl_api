package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.bean.PitchingStatPlayerBean;
import com.main.pcblroyals.dao.CareerPitchingStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("careerPitchingStatService")
public class CareerPitchingStatService {

    @Autowired
    @Qualifier("careerPitchingStatDao")
    private CareerPitchingStatDao careerPitchingStatDao;

//    @Transactional
//    public Player getPlayerById(int playerId) {
//
//        return playerDao.selectPlayerById(playerId);
//    }
//
//    @Transactional
//    public void addPlayer(Player player) {
//        playerDao.insertPlayer(player);
//    }
//
//    @Transactional
//    public void modifyPlayer(Player player) {
//        playerDao.updatePlayer(player);
//    }
//
//    @Transactional
//    public List<Player> getAllPlayer() {
//        return playerDao.selectAllPlayer();
//
//    }
//
//    @Transactional
//    public void removePlayer(int playerId) {
//        playerDao.deletePlayer(playerId);
//
//    }

    @Transactional
    public List<PitchingStatPlayerBean> getAllCareerPitchingStats(){
        return careerPitchingStatDao.selectAllCareerPitchingStats();
    }

    @Transactional
    public List<PitchingStatBean> getAllTimePitchingStat(){
        return careerPitchingStatDao.getAllTimePitchingStat();
    }

}
