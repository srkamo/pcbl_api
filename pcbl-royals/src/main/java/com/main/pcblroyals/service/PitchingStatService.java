package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.dao.PitchingStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rblay on 10/11/18.
 */
@Service("pitchingStatService")
public class PitchingStatService {

    @Autowired
    @Qualifier("pitchingStatDao")
    private PitchingStatDao pitchingStatDao;

    @Transactional
    public List<PitchingStatBean> getPitchingStatsBySeason(int seasonId){
        return pitchingStatDao.getPitchingStatsBySeason(seasonId);
    }
}
