package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleGameRecordBean;
import com.main.pcblroyals.bean.SingleSeasonRecordBean;
import com.main.pcblroyals.dao.SingleGamePitchingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Service("singleGamePitchingRecordService")
public class SingleGamePitchingRecordService {

    @Autowired
    @Qualifier("singleGamePitchingRecordDao")
    private SingleGamePitchingRecordDao singleGamePitchingRecordDao;

    @Transactional
    public List<SingleGameRecordBean> getSingleSeasonPitchingRecordStrikeouts(){
        return singleGamePitchingRecordDao.getSingleGamePitchingRecordStrikeouts();
    }
}
