package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.BattingStatBean;
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
    public List<BattingStatBean> getBattingStatsBySeason(int seasonId){
        return battingStatDao.getBattingStatsBySeason(seasonId);
    }
}
