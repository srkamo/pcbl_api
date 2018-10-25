package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.RecordBean;
import com.main.pcblroyals.dao.AllTimeBattingRecordDao;
import com.main.pcblroyals.dao.AllTimePitchingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rblay on 10/25/18.
 */
@Service("allTimePitchingRecordService")
public class AllTimePitchingRecordService {

    @Autowired
    @Qualifier("allTimePitchingRecordDao")
    private AllTimePitchingRecordDao allTimePitchingRecordDao;

    public List<RecordBean> getAllTimePitchingRecordGamesPitched(){
        return allTimePitchingRecordDao.getAllTimePitchingRecordGamesPitched();
    }

    public List<RecordBean> getAllTimePitchingRecordWins(){
        return allTimePitchingRecordDao.getAllTimePitchingRecordWins();
    }

    public List<RecordBean> getAllTimePitchingRecordSaves(){
        return allTimePitchingRecordDao.getAllTimePitchingRecordSaves();
    }

    public List<RecordBean> getAllTimePitchingRecordERA(){
        return allTimePitchingRecordDao.getAllTimePitchingRecordERA();
    }

    public List<RecordBean> getAllTimePitchingRecordWHIP(){
        return allTimePitchingRecordDao.getAllTimePitchingRecordWHIP();
    }

    public List<RecordBean> getAllTimePitchingRecordCyYoung(){
        return allTimePitchingRecordDao.getAllTimePitchingRecordCyYoung();
    }

}
