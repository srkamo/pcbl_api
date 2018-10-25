package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleSeasonBattingRecordBean;
import com.main.pcblroyals.bean.SingleSeasonPitchingRecordBean;
import com.main.pcblroyals.dao.SingleSeasonBattingRecordDao;
import com.main.pcblroyals.dao.SingleSeasonPitchingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Service("singleSeasonPitchingRecordService")
public class SingleSeasonPitchingRecordService {

    @Autowired
    @Qualifier("singleSeasonPitchingRecordDao")
    private SingleSeasonPitchingRecordDao singleSeasonPitchingRecordDao;


    @Transactional
    public List<SingleSeasonPitchingRecordBean> getSingleSeasonPitchingRecordWins(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordWins();
    }

    @Transactional
    public List<SingleSeasonPitchingRecordBean> getSingleSeasonPitchingRecordSaves(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordSaves();
    }

    @Transactional
    public List<SingleSeasonPitchingRecordBean> getSingleSeasonPitchingRecordStrikeouts(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordStrikeouts();
    }

    @Transactional
    public List<SingleSeasonPitchingRecordBean> getSingleSeasonPitchingRecordERA(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordERA();
    }

    @Transactional
    public List<SingleSeasonPitchingRecordBean> getSingleSeasonPitchingRecordWHIP(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordWHIP();
    }

    @Transactional
    public List<SingleSeasonPitchingRecordBean> getSingleSeasonPitchingRecordCyYoung(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordCyYoung();
    }

}
