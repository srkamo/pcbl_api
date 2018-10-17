package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.AllTimeSeasonBean;
import com.main.pcblroyals.data.Season;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("seasonDao")
public class SeasonDao {

    @PersistenceContext
    EntityManager entityManager;

    public Season selectSeasonById(int seasonId) {
        return entityManager.find(Season.class, seasonId);
    }

    public void insertSeason(Season season) {
        entityManager.persist(season);
    }

    public void updateSeason(Season season) {

        Season seasonToUpdate = selectSeasonById(season.getId());

        seasonToUpdate.setSeason(seasonToUpdate.getSeason());
        seasonToUpdate.setYear(seasonToUpdate.getYear());
        seasonToUpdate.setDivision(seasonToUpdate.getDivision());
        seasonToUpdate.setPlayers(seasonToUpdate.getPlayers());

        entityManager.flush();
    }

    public void deleteSeason(int seasonId) {
        entityManager.remove(selectSeasonById(seasonId));
    }

    public List<Season> selectAllSeason() {
        String q = "select s from seasons s";
        Query query = entityManager.createQuery(q);
        return (List<Season>) query.getResultList();
    }

    public List<Season> getAllSeasonReverseChronological() {
        String q = "select s from seasons s order by id desc";
        Query query = entityManager.createQuery(q);
        return (List<Season>) query.getResultList();

    }

    public List<AllTimeSeasonBean> getAllTimeRecord(){
        String q = "select new com.main.pcblroyals.bean.AllTimeSeasonBean" +
                "(sum(s.wins), sum(s.ties), sum(s.losses)) " +
                "from seasons s";
        Query query = entityManager.createQuery(q);
        return (List<AllTimeSeasonBean>) query.getResultList();
    }

}
