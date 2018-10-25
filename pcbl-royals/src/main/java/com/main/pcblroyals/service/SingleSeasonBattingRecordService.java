package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleSeasonBattingRecordBean;
import com.main.pcblroyals.dao.SingleSeasonBattingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Service("singleSeasonBattingRecordService")
public class SingleSeasonBattingRecordService {

    @Autowired
    @Qualifier("singleSeasonBattingRecordDao")
    private SingleSeasonBattingRecordDao singleSeasonBattingRecordDao;

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHits(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordHits();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordDoubles(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordDoubles();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordTriples(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordTriples();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHomeruns(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordHomeruns();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordRuns(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordRuns();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordRbis(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordRbis();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordStolenBases(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordStolenBases();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordWalks(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordWalks();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHitByPitch(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordHitByPitch();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordBattingAverage(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordBattingAverage();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordSluggingPercentage(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordSluggingPercentage();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordOnBasePercentage(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordOnBasePercentage();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordMVP(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordMVP();
    }
}
