package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.BattingStatBean;
import com.main.pcblroyals.bean.BattingStatPlayerBean;
import com.main.pcblroyals.bean.BattingStatSeasonBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("battingStatDao")
public class BattingStatDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<BattingStatPlayerBean> getBattingStatsBySeason(int seasonId){
        String q = "select new com.main.pcblroyals.bean.BattingStatPlayerBean(" +
                "b.player.id, b.player.firstName, b.player.lastName, count(b.player.id), " +
                "sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                "sum(b.walks), sum(b.hitByPitch),sum(b.sacrifices),sum(b.runs),sum(b.rbis)," +
                "sum(b.stolenBases),sum(b.passedBalls),sum(b.caughtStealing),sum(b.strikeOuts)" +
                "" +
                ")  " +
                " from batting_stats b " +
                " where b.game.season.id = " + seasonId +
                " group by b.player.id, b.player.firstName, b.player.lastName " +
                " order by b.player.lastName";

        Query query = entityManager.createQuery(q);
        return (List<BattingStatPlayerBean>) query.getResultList();
    }

    public List<BattingStatPlayerBean> getBattingStatsBySeasonGame(int seasonId, int gameId){
        String q = "select new com.main.pcblroyals.bean.BattingStatPlayerBean(" +
                "b.player.id, b.player.firstName, b.player.lastName, count(b.player.id), " +
                "sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                "sum(b.walks), sum(b.hitByPitch),sum(b.sacrifices),sum(b.runs),sum(b.rbis)," +
                "sum(b.stolenBases),sum(b.passedBalls),sum(b.caughtStealing),sum(b.strikeOuts)" +
                "" +
                ")  " +
                " from batting_stats b " +
                " where b.game.season.id = " + seasonId +
                " and b.game.id = " + gameId +
                " group by b.player.id, b.player.firstName, b.player.lastName " +
                " order by b.player.lastName";

        Query query = entityManager.createQuery(q);
        return (List<BattingStatPlayerBean>) query.getResultList();
    }

    public List<BattingStatSeasonBean> getBattingStatsSeasonByPlayer(int playerId){
        String q = "select new com.main.pcblroyals.bean.BattingStatSeasonBean(" +
                "b.game.season.id, b.game.season.season, b.game.season.year, count(b.game.season.id), " +
                "sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                "sum(b.walks), sum(b.hitByPitch),sum(b.sacrifices),sum(b.runs),sum(b.rbis)," +
                "sum(b.stolenBases),sum(b.passedBalls),sum(b.caughtStealing),sum(b.strikeOuts)" +
                "" +
                ")  " +
                " from batting_stats b " +
                " where b.player.id = " + playerId +
                " group by b.game.season.id, b.game.season.season, b.game.season.year " +
                " order by b.game.season.year, b.game.season.season desc";

        Query query = entityManager.createQuery(q);
        return (List<BattingStatSeasonBean>) query.getResultList();
    }
}
