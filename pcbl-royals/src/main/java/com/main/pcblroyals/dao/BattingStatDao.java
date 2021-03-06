package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.*;
import com.main.pcblroyals.data.BattingStat;
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

    public List<BattingStat> getBattingStatForSeasonGamePlayer(int seasonId, int gameId, int playerId){
        String q = "select b FROM batting_stats b " +
                " WHERE b.player.id = " + playerId +
                " AND b.game.id = " + gameId +
                " AND b.game.season.id = " + seasonId;

        Query query = entityManager.createQuery(q);
        return (List<BattingStat>) query.getResultList();
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
        if(checkPlayerHasBattedSeason(seasonId, playerId)){
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
        else{
            List<BattingStatGameBean> battingStatGameBean = new ArrayList<BattingStatGameBean>();
            BattingStatGameBean tempBean = new BattingStatGameBean();
            battingStatGameBean.add(tempBean);
            return battingStatGameBean;
        }
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

    // get the stats for the whole team for this season and this game
    public List<BattingStatBean> getTeamStatsForGame(int seasonId, int gameId){
        String q = "select new com.main.pcblroyals.bean.BattingStatBean" +
                "(sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                "sum(b.walks), sum(b.hitByPitch), sum(b.sacrifices), sum(b.runs), sum(b.rbis), " +
                "sum(b.stolenBases), sum(b.passedBalls), sum(b.caughtStealing), sum(b.strikeOuts)) " +
                "from batting_stats b where b.game.season.id = " + seasonId
                + " and b.game.id = " + gameId;

        Query query = entityManager.createQuery(q);
        return (List<BattingStatBean>) query.getResultList();
    }

    // get the all time batting stats for the player
    public List<BattingStatBean> getAllTimeBattingStatsForPlayer(int playerId){
        if(checkPlayerHasBattedAllTime(playerId)){
            String q = "select new com.main.pcblroyals.bean.BattingStatBean" +
                    "(sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                    "sum(b.walks), sum(b.hitByPitch), sum(b.sacrifices), sum(b.runs), sum(b.rbis), " +
                    "sum(b.stolenBases), sum(b.passedBalls), sum(b.caughtStealing), sum(b.strikeOuts)) " +
                    "from batting_stats b where b.player.id = " + playerId;

            Query query = entityManager.createQuery(q);
            return (List<BattingStatBean>) query.getResultList();
        }
        else{
            List<BattingStatBean> battingStatBeanList = new ArrayList<BattingStatBean>();
            BattingStatBean tempBean = new BattingStatBean();
            battingStatBeanList.add(tempBean);
            return battingStatBeanList;
        }
    }

    //get the all time batting stats for player in a season
    public List<BattingStatBean> getTotalBattingStatsForPlayerBySeason(int seasonId, int playerId){
        if(checkPlayerHasBattedSeason(seasonId, playerId)){
            String q = "select new com.main.pcblroyals.bean.BattingStatBean" +
                    "(sum(b.atBats), sum(b.singles), sum(b.doubles), sum(b.triples), sum(b.homeRuns), " +
                    "sum(b.walks), sum(b.hitByPitch), sum(b.sacrifices), sum(b.runs), sum(b.rbis), " +
                    "sum(b.stolenBases), sum(b.passedBalls), sum(b.caughtStealing), sum(b.strikeOuts)) " +
                    "from batting_stats b where b.player.id = " + playerId + " and b.game.season.id = " + seasonId;

            Query query = entityManager.createQuery(q);
            return (List<BattingStatBean>) query.getResultList();
        }
        else{
            List<BattingStatBean> battingStatBeanList = new ArrayList<BattingStatBean>();
            BattingStatBean tempBean = new BattingStatBean();
            battingStatBeanList.add(tempBean);
            return battingStatBeanList;
        }
    }

    public void insertBattingStat(BattingStat battingStat){
        int playerId = battingStat.getPlayer().getId();
        int gameId = battingStat.getGame().getId();
        if(!checkBattingStatExists(gameId,playerId)){
            entityManager.persist(battingStat);
        }
    }

    public boolean checkPlayerHasBattedAllTime(int playerId){
        String preQ = "SELECT * FROM batting_stats WHERE player_id = " + playerId;
        Query preQuery = entityManager.createNativeQuery(preQ);
        List<Object[]> preQueryObject = preQuery.getResultList();
        return (preQueryObject.size() > 0);
    }

    public boolean checkPlayerHasBattedSeason(int seasonId, int playerId){
        String preQ = "SELECT b.player_id " +
                " FROM batting_stats b " +
                " JOIN games g ON b.game_id = g.id" +
                " WHERE b.player_id = " + playerId +
                " AND g.season_id = " + seasonId;
        Query preQuery = entityManager.createNativeQuery(preQ);
        List<Object[]> preQueryObject = preQuery.getResultList();
        return (preQueryObject.size() > 0);
    }

    public boolean checkBattingStatExists(int gameId, int playerId){
        String preQ = "SELECT b.player_id " +
                " FROM batting_stats b " +
                " JOIN games g ON b.game_id = g.id" +
                " WHERE b.player_id = " + playerId +
                " AND g.id = " + gameId;
        Query preQuery = entityManager.createNativeQuery(preQ);
        List<Object[]> preQueryObject = preQuery.getResultList();
        return (preQueryObject.size() > 0);
    }

}
