package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.AllTimeSeasonBean;
import com.main.pcblroyals.bean.SeasonResultsBean;
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
        String q = "select s from seasons s order by year desc, season, id desc";
        Query query = entityManager.createQuery(q);
        return (List<Season>) query.getResultList();
    }

    // w-l-t across all seasons
    public List<AllTimeSeasonBean> getAllTimeRecord(){
        String q = "select new com.main.pcblroyals.bean.AllTimeSeasonBean( " +
                "sum(case when g.teamScore > g.opponentScore then 1 else 0 end), " +
                "sum(case when g.teamScore = g.opponentScore then 1 else 0 end), " +
                "sum(case when g.teamScore < g.opponentScore then 1 else 0 end) " +
                ") " +
                "from seasons s " +
                "join games g on s.id = g.season.id ";
        Query query = entityManager.createQuery(q);
        return (List<AllTimeSeasonBean>) query.getResultList();
    }

    public List<SeasonResultsBean> getAllSeasonResults(){
        String q = "select new com.main.pcblroyals.bean.SeasonResultsBean( " +
                "s.id, s.season, s.year, " +
                "sum(case when g.teamScore > g.opponentScore then 1 else 0 end), " +
                "sum(case when g.teamScore = g.opponentScore then 1 else 0 end), " +
                "sum(case when g.teamScore < g.opponentScore then 1 else 0 end) " +
                ") " +
                "from seasons s " +
                "join games g on s.id = g.season.id " +
                "group by s.id, s.season, s.year";
        Query query = entityManager.createQuery(q);
        return (List<SeasonResultsBean>) query.getResultList();
    }

}
