package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.bean.PitchingStatPlayerBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("careerPitchingStatDao")
public class CareerPitchingStatDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<PitchingStatPlayerBean> selectAllCareerPitchingStats(){
        String q = "select new com.main.pcblroyals.bean.PitchingStatPlayerBean(" +
                "p.player.id, p.player.firstName, p.player.lastName, count(p.player.id), " +
                "sum(case when p.result = 1 then 1 else 0 end), " +
                "sum(case when p.result = 2 then 1 else 0 end), " +
                "sum(case when p.result = 4 then 1 else 0 end), " +
                "sum(case when p.result = 3 then 1 else 0 end), " +
                "SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)), " +
                "sum(earnedRuns)," +
                "sum(totalRuns), " +
                "sum(strikeouts), " +
                "sum(walks), " +
                "sum(hitByPitch), " +
                "sum(hits), " +
                "sum(wildPitches), " +
                "sum(stolenBases), " +
                "sum(pickoffs) " +
                ") " +
                "from pitching_stats p " +
                " group by p.player.id, p.player.firstName, p.player.lastName " +
                " order by p.player.lastName, p.player.firstName";
        Query query = entityManager.createQuery(q);
        return (List<PitchingStatPlayerBean>) query.getResultList();
    }

    public List<PitchingStatBean> getAllTimePitchingStat(){
        String q = "select new com.main.pcblroyals.bean.PitchingStatBean(" +
                "sum(case when p.result = 1 then 1 else 0 end), " +
                "sum(case when p.result = 2 then 1 else 0 end), " +
                "sum(case when p.result = 4 then 1 else 0 end), " +
                "sum(case when p.result = 3 then 1 else 0 end), " +
                "SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)), " +
                "sum(earnedRuns)," +
                "sum(totalRuns), " +
                "sum(strikeouts), " +
                "sum(walks), " +
                "sum(hitByPitch), " +
                "sum(hits), " +
                "sum(wildPitches), " +
                "sum(stolenBases), " +
                "sum(pickoffs) " +
                ") " +
                "from pitching_stats p ";

        Query query = entityManager.createQuery(q);
        return (List<PitchingStatBean>) query.getResultList();
    }

}
