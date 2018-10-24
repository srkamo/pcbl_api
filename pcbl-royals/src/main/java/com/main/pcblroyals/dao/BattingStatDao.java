package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
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
                " order by b.player.lastName, b.player.firstName";

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
                " order by b.player.lastName, b.player.firstName";

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

    public List<BattingStatGameBean> getBattingStatsGameBySeasonPlayer(int seasonId, int playerId){
        String q = "select new com.main.pcblroyals.bean.BattingStatGameBean(" +
                "b.game.id, b.game.opponent.id, b.game.opponent.name, b.game.homeTeam, b.game.date,  " +
                "sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                "sum(b.walks), sum(b.hitByPitch),sum(b.sacrifices),sum(b.runs),sum(b.rbis)," +
                "sum(b.stolenBases),sum(b.passedBalls),sum(b.caughtStealing),sum(b.strikeOuts)" +
                "" +
                ")  " +
                " from batting_stats b " +
                " where b.game.season.id = " + seasonId +
                " and b.player.id = " + playerId +
                " group by b.game.id, b.game.opponent.id, b.game.opponent.name, b.game.homeTeam, b.game.date " +
                " order by b.game.date";

        Query query = entityManager.createQuery(q);
        return (List<BattingStatGameBean>) query.getResultList();

    }

    // get the all time batting stat line for a single season
    public List<BattingStatBean> getAllTimeBattingStatForSeason(int seasonId){
        String q = "select new com.main.pcblroyals.bean.BattingStatBean" +
                "(sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                "sum(b.walks), sum(b.hitByPitch), sum(b.sacrifices), sum(b.runs), sum(b.rbis), " +
                "sum(b.stolenBases), sum(b.passedBalls), sum(b.caughtStealing), sum(b.strikeOuts)) " +
                "from batting_stats b where b.game.season.id = " + seasonId;

        Query query = entityManager.createQuery(q);
        return (List<BattingStatBean>) query.getResultList();
    }
}
