package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.dao.CareerBattingStatDao;
import com.main.pcblroyals.dao.CareerPitchingStatDao;
import com.main.pcblroyals.dao.PlayerDao;
import com.main.pcblroyals.data.CareerBattingStat;
import com.main.pcblroyals.data.CareerPitchingStat;
import com.main.pcblroyals.data.Player;
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
    public List<PitchingStatBean> getAllCareerPitchingStats(){
        return careerPitchingStatDao.selectAllCareerPitchingStats();
    }

}
