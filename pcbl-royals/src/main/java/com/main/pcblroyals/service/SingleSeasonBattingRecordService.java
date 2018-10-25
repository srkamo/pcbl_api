package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleSeasonRecordBean;
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
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordHits(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordHits();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordDoubles(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordDoubles();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordTriples(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordTriples();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordHomeruns(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordHomeruns();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordRuns(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordRuns();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordRbis(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordRbis();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordStolenBases(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordStolenBases();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordWalks(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordWalks();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordHitByPitch(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordHitByPitch();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordBattingAverage(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordBattingAverage();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordSluggingPercentage(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordSluggingPercentage();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordOnBasePercentage(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordOnBasePercentage();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordMVP(){
        return singleSeasonBattingRecordDao.getSingleSeasonBattingRecordMVP();
    }
}
