package com.main.pcblroyals.service;

import com.main.pcblroyals.bean.AllTimeSeasonBean;
import com.main.pcblroyals.bean.SeasonResultsBean;
import com.main.pcblroyals.dao.SeasonDao;
import com.main.pcblroyals.data.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("seasonService")
public class SeasonService {

    @Autowired
    @Qualifier("seasonDao")
    private SeasonDao seasonDao;

    @Transactional
    public Season getSeasonById(int seasonId) {
        return seasonDao.selectSeasonById(seasonId);
    }

    @Transactional
    public void addSeason(Season season) {
        seasonDao.insertSeason(season);
    }

    @Transactional
    public void modifySeason(Season season) {
        seasonDao.updateSeason(season);
    }

    @Transactional
    public List<Season> getAllSeason() {
        return seasonDao.selectAllSeason();
    }

    @Transactional
    public List<Season> getAllSeasonReverseChronological() {
        return seasonDao.getAllSeasonReverseChronological();
    }

    @Transactional
    public void removeSeason(int seasonId) {
        seasonDao.deleteSeason(seasonId);
    }

    @Transactional
    public List<AllTimeSeasonBean> getAllTimeRecord(){
        return seasonDao.getAllTimeRecord();
    }

    @Transactional
    public List<SeasonResultsBean> getAllSeasonResults(){
        return seasonDao.getAllSeasonResults();
    }

    @Transactional
    public int getCurrSeasonId(){
        List<Season> seasonList = getAllSeasonReverseChronological();
        int currSeasonId = seasonList.get(0).getId();
        return currSeasonId;
    }

}
