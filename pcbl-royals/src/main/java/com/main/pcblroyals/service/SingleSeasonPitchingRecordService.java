package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleSeasonRecordBean;
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
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordWins(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordWins();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordSaves(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordSaves();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordStrikeouts(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordStrikeouts();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordERA(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordERA();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordWHIP(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordWHIP();
    }

    @Transactional
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordCyYoung(){
        return singleSeasonPitchingRecordDao.getSingleSeasonPitchingRecordCyYoung();
    }

}
