package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleGameRecordBean;
import com.main.pcblroyals.dao.SingleGameBattingRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
/**
 * Created by rblay on 10/24/18.
 */
@Service("singleGameBattingRecordService")
public class SingleGameBattingRecordService {

    @Autowired
    @Qualifier("singleGameBattingRecordDao")
    private SingleGameBattingRecordDao singleGameBattingRecordDao;

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordHits(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordHits();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordDoubles(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordDoubles();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordTriples(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordTriples();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordHomeruns(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordHomeruns();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordRuns(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordRuns();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordRbis(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordRbis();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordStolenBases(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordStolenBases();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordWalks(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordWalks();
    }

    @Transactional
    public List<SingleGameRecordBean> getSingleGameBattingRecordHitByPitch(){
        return singleGameBattingRecordDao.getSingleGameBattingRecordHitByPitch();
    }



}
