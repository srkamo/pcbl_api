package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.RecordBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rblay on 10/25/18.
 */
@Repository("allTimePitchingRecordDao")
public class AllTimePitchingRecordDao {

    @PersistenceContext
    EntityManager entityManager;

    protected int singleSeasonPitchingGameFilter = 10;

    public List<RecordBean> getTopPlayersAllTimePitchingForStat(String statQuery, String recordName){
        String q = statQuery;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allStatRecords = query.getResultList();

        //Selecting only first 4 names (plus tied people if needed)
        BigDecimal lastRecordAdded = BigDecimal.valueOf(Integer.MIN_VALUE);
        List<RecordBean> topPlayersList = new ArrayList<RecordBean>();
        int numPeopleAdded = 0;
        for(Object[] record:allStatRecords){
            BigDecimal tempRecord;
            String recordString = (String)record[2] + ", " + (String)record[1];

            if(recordName == "games_pitched" || recordName == "cy_young"){
                BigInteger tempRecordInt = (BigInteger)record[3];
                tempRecord = BigDecimal.valueOf(tempRecordInt.intValue());
            }
            else{
                tempRecord = (BigDecimal) record[3];
            }

            if(numPeopleAdded >= 5 && !tempRecord.equals(lastRecordAdded)){
                break;
            } else {
                RecordBean tempBean = new RecordBean(recordString,recordName,tempRecord);
                topPlayersList.add(tempBean);
            }
            lastRecordAdded = tempRecord;
            numPeopleAdded += 1;
        }
        return topPlayersList;
    }

    public List<RecordBean> getAllTimePitchingRecordGamesPitched(){
        String q = "SELECT  " +
                "  s.player_id, " +
                "  p.first_name, " +
                "  p.last_name, " +
                "  COUNT(1) AS gamesPitched " +
                "FROM  " +
                "  pitching_stats s " +
                "JOIN " +
                "  players p " +
                "ON " +
                "  s.player_id = p.id " +
                "GROUP BY  " +
                "  s.player_id, p.first_name, p.last_name   " +
                "ORDER BY  " +
                "  4 DESC";
        return getTopPlayersAllTimePitchingForStat(q,"games_pitched");
    }

    public List<RecordBean> getAllTimePitchingRecordWins(){
        String q = "SELECT   " +
                "  s.player_id, p.first_name, p.last_name,   " +
                "    SUM(  " +
                "    CASE WHEN s.result = 1 THEN 1 ELSE 0   " +
                "  END) AS wins  " +
                "FROM   " +
                "  pitching_stats s  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  s.player_id = p.id  " +
                "GROUP BY   " +
                "  s.player_id, p.first_name, p.last_name    " +
                "ORDER BY   " +
                "  4 DESC";
        return getTopPlayersAllTimePitchingForStat(q,"wins");
    }

    public List<RecordBean> getAllTimePitchingRecordSaves(){
        String q = "SELECT   " +
                "  s.player_id, p.first_name, p.last_name,   " +
                "    SUM(  " +
                "    CASE WHEN s.result = 3 THEN 1 ELSE 0   " +
                "        END) AS saves  " +
                "FROM   " +
                "  pitching_stats s  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  s.player_id = p.id  " +
                "GROUP BY   " +
                "  s.player_id, p.first_name, p.last_name  " +
                "ORDER BY   " +
                "  4 DESC";
        return getTopPlayersAllTimePitchingForStat(q,"saves");
    }

    public List<RecordBean> getAllTimePitchingRecordERA(){
        String q = "SELECT   " +
                "   s.player_id, " +
                "   p.first_name, " +
                "   p.last_name,  " +
                "    CASE  " +
                "    WHEN SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) = 0 THEN 0  " +
                "        ELSE ROUND((SUM(s.earned_runs)*9)/(SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))),2)  " +
                "  END AS era,  " +
                "    SUM(s.earned_runs)*9 as earnedRuns,  " +
                "    SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) AS innings  " +
                "FROM   " +
                "  pitching_stats s  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  s.player_id = p.id  " +
                "GROUP BY   " +
                "  s.player_id, p.first_name, p.last_name  " +
                "HAVING  " +
                "  COUNT(1) > " + Integer.toString(singleSeasonPitchingGameFilter) + " " +
                "ORDER BY   " +
                "  4 ASC";
        return getTopPlayersAllTimePitchingForStat(q,"era");
    }

    public List<RecordBean> getAllTimePitchingRecordWHIP(){
        String q = "SELECT   " +
                "  s.player_id, p.first_name, p.last_name,  " +
                "    CASE  " +
                "    WHEN SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) = 0 THEN 0  " +
                "        ELSE ROUND((SUM(s.hits)+SUM(s.walks))/(SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))),2)  " +
                "  END AS whip,  " +
                "    SUM(s.hits) as hits,  " +
                "    SUM(s.walks) as walks,  " +
                "    SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) AS innings  " +
                "FROM   " +
                "  pitching_stats s  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  s.player_id = p.id  " +
                "GROUP BY   " +
                "  s.player_id, p.first_name, p.last_name  " +
                "HAVING  " +
                "  COUNT(1) > " + Integer.toString(singleSeasonPitchingGameFilter) + " " +
                "ORDER BY   " +
                "  4 ASC";
        return getTopPlayersAllTimePitchingForStat(q,"whip");
    }

    public List<RecordBean> getAllTimePitchingRecordCyYoung(){
        String q = "SELECT   " +
                "   t4.player_id,   " +
                "    t4.first_name,   " +
                "    t4.last_name,   " +
                "    COUNT(1) num_cy_young   " +
                "FROM      " +
                "(   " +
                "SELECT    " +
                "   t1.player_id,   " +
                "   t1.first_name,   " +
                "   t1.last_name,   " +
                "   t1.season,   " +
                "   t1.year,   " +
                "   t1.season_id,   " +
                "    t1.cy_young   " +
                "FROM   " +
                "   (SELECT    " +
                "      ps.player_id,    " +
                "      p.first_name,    " +
                "      p.last_name,    " +
                "      s.season,    " +
                "      s.year,   " +
                "      g.season_id,   " +
                "      SUM(CASE WHEN ps.result = 1 THEN 1 ELSE 0 END)*3 - SUM(CASE WHEN ps.result = 2 THEN 1 ELSE 0 END)*2 +    " +
                "      SUM(CASE WHEN ps.result = 3 THEN 1 ELSE 0 END)*2 + SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))*3 +   " +
                "      SUM(strikeouts) - SUM(walks) - SUM(hitbypitch) - SUM(earned_runs) - SUM(hits) AS cy_young   " +
                "   FROM    " +
                "      pitching_stats ps   " +
                "   JOIN players p ON ps.player_id = p.id   " +
                "   JOIN games g ON ps.game_id = g.id   " +
                "   JOIN seasons s ON g.season_id = s.id   " +
                "   GROUP BY    " +
                "      ps.player_id,    " +
                "      p.first_name,    " +
                "      p.last_name,    " +
                "      s.season,    " +
                "      s.year,   " +
                "    g.season_id   " +
                "   ) t1    " +
                "JOIN   " +
                "   (SELECT   " +
                "      season_id, MAX(cy_young) AS max_cy_young   " +
                "   FROM (   " +
                "      SELECT    " +
                "         ps.player_id,    " +
                "         g.season_id,   " +
                "         SUM(CASE WHEN ps.result = 1 THEN 1 ELSE 0 END)*3 - SUM(CASE WHEN ps.result = 2 THEN 1 ELSE 0 END)*2 +    " +
                "         SUM(CASE WHEN ps.result = 3 THEN 1 ELSE 0 END)*2 + SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))*3 +   " +
                "         SUM(strikeouts) - SUM(walks) - SUM(hitbypitch) - SUM(earned_runs) - SUM(hits) AS cy_young   " +
                "      FROM    " +
                "         pitching_stats ps   " +
                "      JOIN players p ON ps.player_id = p.id   " +
                "      JOIN games g ON ps.game_id = g.id   " +
                "      JOIN seasons s ON g.season_id = s.id   " +
                "      GROUP BY    " +
                "         ps.player_id,    " +
                "         g.season_id   " +
                "      ) t2       " +
                "   GROUP BY   " +
                "      season_id   " +
                "   ) t3   " +
                "ON   " +
                "   t1.season_id = t3.season_id   " +
                "WHERE   " +
                "   t1.cy_young = t3.max_cy_young      " +
                ") t4   " +
                "GROUP BY    " +
                "   t4.player_id,   " +
                "    t4.first_name,   " +
                "    t4.last_name   " +
                "ORDER BY   " +
                "   4 DESC";
        return getTopPlayersAllTimePitchingForStat(q,"cy_young");
    }
}
