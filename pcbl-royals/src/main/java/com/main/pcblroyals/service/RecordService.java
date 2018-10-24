package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleSeasonBattingRecordBean;
import com.main.pcblroyals.dao.RecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Service("recordService")
public class RecordService {

    @Autowired
    @Qualifier("recordDao")
    private RecordDao recordDao;

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHits(){
        return recordDao.getSingleSeasonBattingRecordHits();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordDoubles(){
        return recordDao.getSingleSeasonBattingRecordDoubles();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordTriples(){
        return recordDao.getSingleSeasonBattingRecordTriples();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHomeruns(){
        return recordDao.getSingleSeasonBattingRecordHomeruns();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordRuns(){
        return recordDao.getSingleSeasonBattingRecordRuns();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordRbis(){
        return recordDao.getSingleSeasonBattingRecordRbis();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordStolenBases(){
        return recordDao.getSingleSeasonBattingRecordStolenBases();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordWalks(){
        return recordDao.getSingleSeasonBattingRecordWalks();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHitByPitch(){
        return recordDao.getSingleSeasonBattingRecordHitByPitch();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordBattingAverage(){
        return recordDao.getSingleSeasonBattingRecordBattingAverage();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordSluggingPercentage(){
        return recordDao.getSingleSeasonBattingRecordSluggingPercentage();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordOnBasePercentage(){
        return recordDao.getSingleSeasonBattingRecordOnBasePercentage();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordMVP(){
        return recordDao.getSingleSeasonBattingRecordMVP();
    }
}
