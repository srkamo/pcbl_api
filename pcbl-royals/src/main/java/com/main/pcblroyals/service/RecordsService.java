package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.SingleSeasonBattingRecordBean;
import com.main.pcblroyals.dao.RecordsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Service("recordsService")
public class RecordsService {

    @Autowired
    @Qualifier("recordsDao")
    private RecordsDao recordsDao;

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHits(){
        return recordsDao.getSingleSeasonBattingRecordHits();
    }

    @Transactional
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordDoubles(){
        return recordsDao.getSingleSeasonBattingRecordDoubles();
    }
}
