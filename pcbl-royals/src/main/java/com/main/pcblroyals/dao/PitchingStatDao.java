package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.bean.PitchingStatGameBean;
import com.main.pcblroyals.bean.PitchingStatPlayerBean;
import com.main.pcblroyals.bean.PitchingStatSeasonBean;
import com.main.pcblroyals.data.PitchingStat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("pitchingStatDao")
public class PitchingStatDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<PitchingStatPlayerBean> getPitchingStatsBySeason(int seasonId){
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
                "where p.game.season.id = " + seasonId +
                " group by p.player.id, p.player.firstName, p.player.lastName " +
                " order by p.player.lastName, p.player.firstName";

        Query query = entityManager.createQuery(q);
        return (List<PitchingStatPlayerBean>) query.getResultList();
    }

    public List<PitchingStatPlayerBean> getPitchingStatsBySeasonGame(int seasonId, int gameId){

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
                " from pitching_stats p " +
                " where p.game.season.id = " + seasonId +
                " and p.game.id = " + gameId +
                " group by p.player.id, p.player.firstName, p.player.lastName " +
                " order by p.player.lastName, p.player.firstName";

        Query query = entityManager.createQuery(q);
        return (List<PitchingStatPlayerBean>) query.getResultList();
    }

    public List<PitchingStat> getPitchingStatForSeasonGamePlayer(int seasonId, int gameId, int playerId){
        String q = "SELECT p FROM pitching_stats p " +
                " WHERE p.player.id = " + playerId +
                " AND p.game.id = " + gameId +
                " AND p.game.season.id = " + seasonId;

        Query query = entityManager.createQuery(q);
        return (List<PitchingStat>) query.getResultList();
    }

    public List<PitchingStatSeasonBean> getPitchingStatsSeasonByPlayer(int playerId){
        if(checkPlayerHasPitchedAllTime(playerId)){
            String q = "select new com.main.pcblroyals.bean.PitchingStatSeasonBean(" +
                    "p.game.season.id, p.game.season.season, p.game.season.year, count(p.player.id), " +
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
                    " from pitching_stats p " +
                    " where p.player.id = " + playerId +
                    " group by p.game.season.id, p.game.season.season, p.game.season.year " +
                    " order by p.player.lastName, p.player.firstName";
            Query query = entityManager.createQuery(q);
            return (List<PitchingStatSeasonBean>) query.getResultList();

        }
        else {
            List<PitchingStatSeasonBean> pitchingStatSeasonBeanList = new ArrayList<PitchingStatSeasonBean>();
            PitchingStatSeasonBean tempBean = new PitchingStatSeasonBean();
            pitchingStatSeasonBeanList.add(tempBean);
            return pitchingStatSeasonBeanList;
        }
    }

    /*
    int game_id, int opponent_id, String opponentName, boolean homeTeam, Date date, long wins, long losses, long ties, long saves, double inningsPitchedRaw, long earnedRuns, long totalRuns, long strikeouts, long walks, long hitByPitch, long hits, long wildPitches, long stolenBases, long pickoffs
     */

    public List<PitchingStatGameBean> getPitchingStatsGameBySeasonPlayer(int seasonId, int playerId){
        if(checkPlayerHasPitchedSeason(seasonId, playerId)){
            String q = "select new com.main.pcblroyals.bean.PitchingStatGameBean(" +
                    "p.game.id, p.game.opponent.id, p.game.opponent.name, p.game.homeTeam, p.game.date,  " +
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
                    " from pitching_stats p " +
                    " where p.game.season.id = " + seasonId +
                    " and p.player.id = " + playerId +
                    " group by p.game.id, p.game.opponent.id, p.game.opponent.name, p.game.homeTeam, p.game.date " +
                    " order by p.game.date";
            Query query = entityManager.createQuery(q);
            return (List<PitchingStatGameBean>) query.getResultList();
        }
        else{
            List<PitchingStatGameBean> pitchingStatGameBeanList = new ArrayList<PitchingStatGameBean>();
            PitchingStatGameBean tempBean = new PitchingStatGameBean();
            pitchingStatGameBeanList.add(tempBean);
            return pitchingStatGameBeanList;
        }
    }

    /*
    long wins, long losses, long ties, long saves, double inningsPitchedRaw, long earnedRuns, long totalRuns, long strikeouts, long walks, long hitByPitch, long hits, long wildPitches, long stolenBases, long pickoffs
     */

    // get the all time pitching stat line for a single season
    public List<PitchingStatBean> getAllTimePitchingStatForSeason(int seasonId){
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
                "from pitching_stats p where p.game.season.id = " + seasonId;

        Query query = entityManager.createQuery(q);
        return (List<PitchingStatBean>) query.getResultList();
    }

    // get the all time pitching stat line for a single game
    public List<PitchingStatBean> getTeamPitchingStatsForGame(int seasonId, int gameId){
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
                "from pitching_stats p where p.game.season.id = " + seasonId + " AND p.game.id = " + gameId;

        Query query = entityManager.createQuery(q);
        return (List<PitchingStatBean>) query.getResultList();
    }

    public List<PitchingStatBean> getAllTimePitchingStatsForPlayer(int playerId){
        if(checkPlayerHasPitchedAllTime(playerId)){
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
                    "from pitching_stats p where p.player.id = " + playerId;

            Query query = entityManager.createQuery(q);
            return (List<PitchingStatBean>) query.getResultList();
        }
        else{
            List<PitchingStatBean> pitchingStatBeanList = new ArrayList<PitchingStatBean>();
            PitchingStatBean tempBean = new PitchingStatBean();
            pitchingStatBeanList.add(tempBean);
            return pitchingStatBeanList;
        }
    }

    public List<PitchingStatBean> getTotalPitchingStatsForPlayerBySeason(int seasonId, int playerId){
        if(checkPlayerHasPitchedSeason(seasonId,playerId)){
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
                    "from pitching_stats p where p.player.id = " + playerId + " and p.game.season.id= " + seasonId;

            Query query = entityManager.createQuery(q);
            return (List<PitchingStatBean>) query.getResultList();
        }
        else {
            List<PitchingStatBean> pitchingStatBeanList = new ArrayList<PitchingStatBean>();
            PitchingStatBean tempBean = new PitchingStatBean();
            pitchingStatBeanList.add(tempBean);
            return pitchingStatBeanList;
        }
    }

    public void insertPitchingStat(PitchingStat pitchingStat){
        int playerId = pitchingStat.getPlayer().getId();
        int gameId = pitchingStat.getGame().getId();
        if(!checkPitchingStatExists(gameId,playerId)){
            entityManager.persist(pitchingStat);
        }
    }

    public boolean checkPlayerHasPitchedAllTime(int playerId){
        String preQ = "SELECT * FROM pitching_stats WHERE player_id = " + playerId;
        Query preQuery = entityManager.createNativeQuery(preQ);
        List<Object[]> preQueryObject = preQuery.getResultList();
        return (preQueryObject.size() > 0);
    }

    public boolean checkPlayerHasPitchedSeason(int seasonId, int playerId){
        String preQ = "SELECT p.player_id " +
                " FROM pitching_stats p " +
                " JOIN games g ON p.game_id = g.id" +
                " WHERE p.player_id = " + playerId +
                " AND g.season_id = " + seasonId;
        Query preQuery = entityManager.createNativeQuery(preQ);
        List<Object[]> preQueryObject = preQuery.getResultList();
        return (preQueryObject.size() > 0);
    }

    public boolean checkPitchingStatExists(int gameId, int playerId){
        String preQ = "SELECT p.player_id " +
                " FROM pitching_stats p " +
                " JOIN games g ON p.game_id = g.id" +
                " WHERE p.player_id = " + playerId +
                " AND g.id = " + gameId;
        Query preQuery = entityManager.createNativeQuery(preQ);
        List<Object[]> preQueryObject = preQuery.getResultList();
        return (preQueryObject.size() > 0);
    }


}
