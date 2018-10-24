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

    //RECORDS SECTION
    public List<SingleSeasonBattingRecordBean> getTopPlayersSingleSeasonForStat(String statQuery, String recordName){
        String q = statQuery;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allStatRecords = query.getResultList();

        //Selecting only first 4 names (plus tied people if needed)
        List<SingleSeasonBattingRecordBean> topPlayersList = new ArrayList<SingleSeasonBattingRecordBean>();
        BigDecimal lastRecordAdded = BigDecimal.valueOf(Integer.MIN_VALUE);
        int numPeopleAdded = 0;
        for(Object[] record:allStatRecords){
            String recordString = (String)record[2] + ", " + (String)record[1];
            String seasonString = (String)record[3] + " " + Integer.toString((int)record[4]);
            BigDecimal tempRecord = (BigDecimal) record[5];
            if(numPeopleAdded >= 5 && !tempRecord.equals(lastRecordAdded)){
                break;
            } else {
                SingleSeasonBattingRecordBean tempBean = new SingleSeasonBattingRecordBean(recordString,seasonString,recordName,tempRecord);
                topPlayersList.add(tempBean);
            }
            lastRecordAdded = tempRecord;
            numPeopleAdded += 1;
        }
        return topPlayersList;
    }

    //hits single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHits(){
        String q = "SELECT  " +
                "    b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    SUM(singles) AS hits " +
                "FROM  " +
                " batting_stats b " +
                "JOIN " +
                " players p " +
                "ON " +
                " b.player_id = p.id " +
                "JOIN " +
                " games g " +
                "ON " +
                " b.game_id = g.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " b.player_id, p.first_name, p.last_name, s.season, s.year " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";

        return getTopPlayersSingleSeasonForStat(q,"hits");
    }

    //hits single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordDoubles(){
        String q = "SELECT  " +
                "    b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    SUM(doubles) AS doubles " +
                "FROM  " +
                " batting_stats b " +
                "JOIN " +
                " players p " +
                "ON " +
                " b.player_id = p.id " +
                "JOIN " +
                " games g " +
                "ON " +
                " b.game_id = g.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " b.player_id, p.first_name, p.last_name, s.season, s.year " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";

        return getTopPlayersSingleSeasonForStat(q,"doubles");
    }


}
