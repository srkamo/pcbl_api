package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.SingleSeasonRecordBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Repository("singleSeasonPitchingRecordDao")
public class SingleSeasonPitchingRecordDao {

    @PersistenceContext
    EntityManager entityManager;

    protected int singleSeasonPitchingGameFilter = 10;

    //SINGLE SEASON RECORDS
    public List<SingleSeasonRecordBean> getTopPlayersSingleSeasonPitchingForStat(String statQuery, String recordName){
        String q = statQuery;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allStatRecords = query.getResultList();

        //Selecting only first 4 names (plus tied people if needed)
        List<SingleSeasonRecordBean> topPlayersList = new ArrayList<SingleSeasonRecordBean>();
        BigDecimal lastRecordAdded = BigDecimal.valueOf(Integer.MIN_VALUE);
        int numPeopleAdded = 0;
        for(Object[] record:allStatRecords){
            String seasonString = (String)record[3] + " " + Integer.toString((int)record[4]);
            String recordString = (String)record[2] + ", " + (String)record[1];

            BigDecimal tempRecord = (BigDecimal) record[5];
            if(numPeopleAdded >= 5 && !tempRecord.equals(lastRecordAdded)){
                break;
            } else {
                SingleSeasonRecordBean tempBean = new SingleSeasonRecordBean(recordString,recordName,tempRecord,seasonString);
                topPlayersList.add(tempBean);
            }
            lastRecordAdded = tempRecord;
            numPeopleAdded += 1;
        }
        return topPlayersList;
    }
    //strikeouts single season record
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordWins(){
        String q = "SELECT  " +
                " ps.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    SUM( " +
                "  CASE WHEN ps.result = 1 THEN 1 ELSE 0  " +
                " END) AS wins " +
                "FROM  " +
                " pitching_stats ps " +
                "JOIN " +
                " players p " +
                "ON " +
                " ps.player_id = p.id " +
                "JOIN " +
                " games g " +
                "ON " +
                " ps.game_id = g.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " ps.player_id, p.first_name, p.last_name, s.season, s.year " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";
        return getTopPlayersSingleSeasonPitchingForStat(q,"wins");
    }

    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordSaves(){
        String q = "SELECT  " +
                " ps.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    SUM( " +
                "  CASE WHEN ps.result = 3 THEN 1 ELSE 0  " +
                " END) AS wins " +
                "FROM  " +
                " pitching_stats ps " +
                "JOIN " +
                " players p " +
                "ON " +
                " ps.player_id = p.id " +
                "JOIN " +
                " games g " +
                "ON " +
                " ps.game_id = g.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " ps.player_id, p.first_name, p.last_name, s.season, s.year " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";
        return getTopPlayersSingleSeasonPitchingForStat(q,"saves");
    }

    //strikeouts single season record
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordStrikeouts(){
        String q = "SELECT  " +
                " ps.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    SUM(strikeouts) AS strikeouts " +
                "FROM  " +
                " pitching_stats ps " +
                "JOIN " +
                " players p " +
                "ON " +
                " ps.player_id = p.id " +
                "JOIN " +
                " games g " +
                "ON " +
                " ps.game_id = g.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " ps.player_id, p.first_name, p.last_name, s.season, s.year " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";
        return getTopPlayersSingleSeasonPitchingForStat(q,"strikeouts");
    }

    //era single season record
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordERA(){
        String q = "SELECT  " +
                "  ps.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    CASE " +
                "    WHEN SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) = 0 THEN 0 " +
                "        ELSE ROUND((SUM(ps.earned_runs)*9)/(SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))),2) " +
                "  END AS era, " +
                "    SUM(ps.earned_runs)*9 as earnedRuns, " +
                "    SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) AS innings " +
                "FROM  " +
                "  pitching_stats ps " +
                "JOIN " +
                "  players p " +
                "ON " +
                "  ps.player_id = p.id " +
                "JOIN " +
                "  games g " +
                "ON " +
                "  ps.game_id = g.id " +
                "JOIN " +
                "  seasons s " +
                "ON " +
                "  g.season_id = s.id " +
                "GROUP BY  " +
                "  ps.player_id, p.first_name, p.last_name, s.season, s.year " +
                "HAVING " +
                " COUNT(1) > " + Integer.toString(singleSeasonPitchingGameFilter) + " " +
                "ORDER BY  " +
                "  6 ASC, s.year, p.last_name, p.first_name";
        return getTopPlayersSingleSeasonPitchingForStat(q,"era");
    }

    //whip single season record
    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordWHIP(){
        String q = "SELECT  " +
                " ps.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    CASE " +
                "  WHEN SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) = 0 THEN 0 " +
                "        ELSE ROUND((SUM(ps.hits)+SUM(ps.walks))/(SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))),2) " +
                " END AS whip, " +
                "    SUM(ps.hits) as hits, " +
                "    SUM(ps.walks) as walks, " +
                "    SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3)) AS innings " +
                "FROM  " +
                " pitching_stats ps " +
                "JOIN " +
                " players p " +
                "ON " +
                " ps.player_id = p.id " +
                "JOIN " +
                " games g " +
                "ON " +
                " ps.game_id = g.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " ps.player_id, p.first_name, p.last_name, s.season, s.year " +
                "HAVING " +
                " COUNT(1) > " + Integer.toString(singleSeasonPitchingGameFilter) + " " +
                "ORDER BY  " +
                " 6 ASC, s.year, p.last_name, p.first_name";
        return getTopPlayersSingleSeasonPitchingForStat(q,"whip");
    }

    public List<SingleSeasonRecordBean> getSingleSeasonPitchingRecordCyYoung(){
        String q = "SELECT   " +
                "  t1.player_id,  " +
                "  t1.first_name,  " +
                "  t1.last_name,  " +
                "  t1.season,  " +
                "  t1.year,  " +
                "  t1.season_id,  " +
                "    t1.cy_young  " +
                "FROM  " +
                "  (SELECT   " +
                "    ps.player_id,   " +
                "    p.first_name,   " +
                "    p.last_name,   " +
                "    s.season,   " +
                "    s.year,  " +
                "    g.season_id,  " +
                "    SUM(CASE WHEN ps.result = 1 THEN 1 ELSE 0 END)*3 - SUM(CASE WHEN ps.result = 2 THEN 1 ELSE 0 END)*2 +   " +
                "    SUM(CASE WHEN ps.result = 3 THEN 1 ELSE 0 END)*2 + SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))*3 +  " +
                "    SUM(strikeouts) - SUM(walks) - SUM(hitbypitch) - SUM(earned_runs) - SUM(hits) AS cy_young  " +
                "  FROM   " +
                "    pitching_stats ps  " +
                "  JOIN players p ON ps.player_id = p.id  " +
                "  JOIN games g ON ps.game_id = g.id  " +
                "  JOIN seasons s ON g.season_id = s.id  " +
                "  GROUP BY   " +
                "    ps.player_id,   " +
                "    p.first_name,   " +
                "    p.last_name,   " +
                "    s.season,   " +
                "    s.year,  " +
                "    g.season_id  " +
                "  ) t1   " +
                "JOIN  " +
                "  (SELECT  " +
                "    season_id, MAX(cy_young) AS max_cy_young  " +
                "  FROM (  " +
                "    SELECT   " +
                "      ps.player_id,   " +
                "      g.season_id,  " +
                "      SUM(CASE WHEN ps.result = 1 THEN 1 ELSE 0 END)*3 - SUM(CASE WHEN ps.result = 2 THEN 1 ELSE 0 END)*2 +   " +
                "      SUM(CASE WHEN ps.result = 3 THEN 1 ELSE 0 END)*2 + SUM(ROUND(innings) + (10 * (innings - ROUND(innings)) / 3))*3 +  " +
                "      SUM(strikeouts) - SUM(walks) - SUM(hitbypitch) - SUM(earned_runs) - SUM(hits) AS cy_young  " +
                "    FROM   " +
                "      pitching_stats ps  " +
                "    JOIN players p ON ps.player_id = p.id  " +
                "    JOIN games g ON ps.game_id = g.id  " +
                "    JOIN seasons s ON g.season_id = s.id  " +
                "    GROUP BY   " +
                "      ps.player_id,   " +
                "      g.season_id  " +
                "    ) t2      " +
                "  GROUP BY  " +
                "    season_id  " +
                "  ) t3  " +
                "ON  " +
                "  t1.season_id = t3.season_id  " +
                "WHERE  " +
                "  t1.cy_young = t3.max_cy_young    " +
                "ORDER BY  " +
                "  t1.year, t1.season DESC ";


        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allMVPRecordsecords = query.getResultList();
        List<SingleSeasonRecordBean> cyYoungList = new ArrayList<SingleSeasonRecordBean>();

        for(Object[] record:allMVPRecordsecords){
            String recordString = (String)record[2] + ", " + (String)record[1];
            String seasonString = (String)record[3] + " " + Integer.toString((int)record[4]);
            BigDecimal tempRecord = (BigDecimal) record[6];

            SingleSeasonRecordBean tempBean = new SingleSeasonRecordBean(recordString,"cy_young",tempRecord,seasonString);
            cyYoungList.add(tempBean);
        }
        return cyYoungList;
    }

}
