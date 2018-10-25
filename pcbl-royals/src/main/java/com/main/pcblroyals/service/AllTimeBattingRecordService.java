package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.RecordBean;
import com.main.pcblroyals.dao.AllTimeBattingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by rblay on 10/25/18.
 */
@Service("allTimeBattingRecordService")
public class AllTimeBattingRecordService {

    @Autowired
    @Qualifier("allTimeBattingRecordDao")
    private AllTimeBattingRecordDao allTimeBattingRecordDao;

    public List<RecordBean> getAllTimeBattingRecordGames(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordGames();
    }

    public List<RecordBean> getAllTimeBattingRecordHits(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordHits();
    }

    public List<RecordBean> getAllTimeBattingRecordDoubles(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordDoubles();
    }

    public List<RecordBean> getAllTimeBattingRecordTriples(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordTriples();
    }

    public List<RecordBean> getAllTimeBattingRecordHomeruns(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordHomeruns();
    }

    public List<RecordBean> getAllTimeBattingRecordRuns(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordRuns();
    }

    public List<RecordBean> getAllTimeBattingRecordRbis(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordRbis();
    }

    public List<RecordBean> getAllTimeBattingRecordStolenBases(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordStolenBases();
    }

    public List<RecordBean> getAllTimeBattingRecordWalks(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordWalks();
    }

    public List<RecordBean> getAllTimeBattingRecordHitByPitch(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordHitByPitch();
    }

    public List<RecordBean> getAllTimeBattingRecordBattingAverage(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordBattingAverage();
    }

    public List<RecordBean> getAllTimeBattingRecordSluggingPercentage(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordSluggingPercentage();
    }

    public List<RecordBean> getAllTimeBattingRecordOnBasePercentage(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordOnBasePercentage();
    }

    public List<RecordBean> getAllTimeBattingRecordMVP(){
        return allTimeBattingRecordDao.getAllTimeBattingRecordMVP();
    }


}
