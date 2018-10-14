package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.PitchingStatBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("pitchingStatDao")
public class PitchingStatDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<PitchingStatBean> getPitchingStatsBySeason(int seasonId){
//        String q = "select new com.main.pcblroyals.bean.BattingStatBean(" +
//                "b.player.id, b.player.firstName, b.player.lastName, count(b.player.id), " +
//                "sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
//                "sum(b.walks), sum(b.hitByPitch),sum(b.sacrifices),sum(b.runs),sum(b.rbis)," +
//                "sum(b.stolenBases),sum(b.passedBalls),sum(b.caughtStealing),sum(b.strikeOuts)" +
//                "" +
//                ")  " +
//                " from batting_stats b " +
//                " where b.game.season.id = " + seasonId +
//                " group by b.player.id, b.player.firstName, b.player.lastName " +
//                " order by b.player.lastName";

        String q = "select new com.main.pcblroyals.bean.PitchingStatBean(" +
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
                "where p.game.season.id = " + seasonId +
                " group by p.player.id, p.player.firstName, p.player.lastName " +
                " order by p.player.lastName";

        Query query = entityManager.createQuery(q);
        return (List<PitchingStatBean>) query.getResultList();
    }

    public List<PitchingStatBean> getPitchingStatsBySeasonGame(int seasonId, int gameId){

        String q = "select new com.main.pcblroyals.bean.PitchingStatBean(" +
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
                "where p.game.season.id = " + seasonId +
                " and p.game.id = " + gameId +
                "group by p.player.id, p.player.firstName, p.player.lastName " +
                " order by p.player.lastName";

        Query query = entityManager.createQuery(q);
        return (List<PitchingStatBean>) query.getResultList();
    }
}
