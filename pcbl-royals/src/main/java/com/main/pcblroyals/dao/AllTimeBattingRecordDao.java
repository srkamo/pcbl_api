package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.RecordBean;
import com.main.pcblroyals.bean.SingleGameRecordBean;
import com.main.pcblroyals.bean.SingleSeasonRecordBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Repository("allTimeBattingRecordDao")
public class AllTimeBattingRecordDao {

    @PersistenceContext
    EntityManager entityManager;

    protected int singleSeasonBattingGameFilter = 20;

    public List<RecordBean> getTopPlayersAllTimeBattingForStat(String statQuery, String recordName){
        String q = statQuery;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allStatRecords = query.getResultList();

        //Selecting only first 4 names (plus tied people if needed)
        List<RecordBean> topPlayersList = new ArrayList<RecordBean>();
        BigDecimal lastRecordAdded = BigDecimal.valueOf(Integer.MIN_VALUE);
        int numPeopleAdded = 0;
        for(Object[] record:allStatRecords){
            String recordString = (String)record[2] + ", " + (String)record[1];

            BigDecimal tempRecord;
            if(recordName == "games" || recordName == "mvp"){
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

    public String makeAllTimeBattingSumQuery(String recordName){
        String q = "SELECT  " +
                "  b.player_id, " +
                "  p.first_name, " +
                "  p.last_name, " +
                "   SUM(" + recordName + ") AS " + recordName + " "+
                "FROM  " +
                "  batting_stats b " +
                "JOIN " +
                "  players p " +
                "ON " +
                "  b.player_id = p.id " +
                "GROUP BY  " +
                "  b.player_id, p.first_name, p.last_name " +
                "ORDER BY  " +
                "  4 DESC;";
        return q;
    }

    public List<RecordBean> getAllTimeBattingRecordGames(){
        String q = "SELECT  " +
                "  b.player_id, " +
                "  p.first_name, " +
                "  p.last_name, " +
                "  COUNT(1) AS games "+
                "FROM  " +
                "  batting_stats b " +
                "JOIN " +
                "  players p " +
                "ON " +
                "  b.player_id = p.id " +
                "GROUP BY  " +
                "  b.player_id, p.first_name, p.last_name " +
                "ORDER BY  " +
                "  4 DESC";
        return getTopPlayersAllTimeBattingForStat(q,"games");

    }

    public List<RecordBean> getAllTimeBattingRecordHits(){
        String recordName = "singles";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"hits");
    }

    public List<RecordBean> getAllTimeBattingRecordDoubles(){
        String recordName = "doubles";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"doubles");
    }

    public List<RecordBean> getAllTimeBattingRecordTriples(){
        String recordName = "triples";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"triples");
    }

    public List<RecordBean> getAllTimeBattingRecordHomeruns(){
        String recordName = "homeruns";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"homeruns");
    }

    public List<RecordBean> getAllTimeBattingRecordRuns(){
        String recordName = "runs";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"runs");
    }

    public List<RecordBean> getAllTimeBattingRecordRbis(){
        String recordName = "rbis";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"rbis");
    }

    public List<RecordBean> getAllTimeBattingRecordStolenBases(){
        String recordName = "stolenbases";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"stolenbases");
    }

    public List<RecordBean> getAllTimeBattingRecordWalks(){
        String recordName = "walks";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"walks");
    }

    public List<RecordBean> getAllTimeBattingRecordHitByPitch(){
        String recordName = "hitbypitch";
        String q = makeAllTimeBattingSumQuery(recordName);
        return getTopPlayersAllTimeBattingForStat(q,"hitbypitch");
    }

    public List<RecordBean> getAllTimeBattingRecordBattingAverage(){
        String q = "SELECT   " +
                "  b.player_id, " +
                "  p.first_name, " +
                "  p.last_name,   " +
                "    CASE WHEN  " +
                "    SUM(atbats) = 0 THEN 0  " +
                "        ELSE ROUND(SUM(singles)/SUM(atbats),3)  " +
                "  END AS batting_average,  " +
                "  SUM(atbats), SUM(singles) " +
                "FROM   " +
                "  batting_stats b  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  b.player_id = p.id  " +
                "GROUP BY   " +
                "  b.player_id, p.first_name, p.last_name  " +
                "HAVING  " +
                "  COUNT(1) > " + Integer.toString(singleSeasonBattingGameFilter) +  " "+
                "ORDER BY   " +
                "  4 DESC";
        return getTopPlayersAllTimeBattingForStat(q,"batting_average");
    }

    public List<RecordBean> getAllTimeBattingRecordSluggingPercentage(){
        String q = "SELECT   " +
                "  b.player_id, " +
                "  p.first_name, " +
                "  p.last_name,   " +
                "  CASE WHEN  " +
                "    SUM(atbats) = 0 THEN 0  " +
                "        ELSE ROUND((SUM(singles)*1 + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4)/SUM(atbats),3)  " +
                "  END AS slugging_percentage,  " +
                "  SUM(atbats), SUM(singles), SUM(doubles), SUM(triples), SUM(homeruns) " +
                "FROM   " +
                "  batting_stats b  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  b.player_id = p.id  " +
                "GROUP BY   " +
                "  b.player_id, p.first_name, p.last_name  " +
                "HAVING  " +
                "  COUNT(1) > " + Integer.toString(singleSeasonBattingGameFilter) +  " "+
                "ORDER BY   " +
                "  4 DESC";
        return getTopPlayersAllTimeBattingForStat(q,"slugging_percentage");
    }

    public List<RecordBean> getAllTimeBattingRecordOnBasePercentage(){
        String q = "SELECT   " +
                "  b.player_id, " +
                "  p.first_name, " +
                "  p.last_name,   " +
                "  CASE WHEN  " +
                "    SUM(atbats) + SUM(walks) + SUM(hitbypitch) + SUM(sacrifices) = 0 THEN 0  " +
                "        ELSE ROUND((SUM(singles) + SUM(walks) + SUM(hitbypitch))/(SUM(atbats) + SUM(walks) + SUM(hitbypitch) + SUM(sacrifices)),3)  " +
                "  END AS on_base_percentage,  " +
                "  SUM(atbats), SUM(singles), SUM(walks), SUM(hitbypitch), SUM(sacrifices) " +
                "FROM  " +
                "  batting_stats b  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  b.player_id = p.id  " +
                "GROUP BY   " +
                "  b.player_id, p.first_name, p.last_name  " +
                "HAVING  " +
                "  COUNT(1) > " + Integer.toString(singleSeasonBattingGameFilter) +  " "+
                "ORDER BY   " +
                "  4 DESC";
        return getTopPlayersAllTimeBattingForStat(q,"on_base_percentage");
    }

    public List<RecordBean> getAllTimeBattingRecordMVP(){
        String q = "SELECT  " +
                "  t4.player_id,  " +
                "    t4.first_name,  " +
                "    t4.last_name,  " +
                "    COUNT(1) num_mvps  " +
                "FROM  " +
                "  " +
                "(  " +
                "SELECT   " +
                "  t1.player_id,  " +
                "  t1.first_name,  " +
                "  t1.last_name,  " +
                "  t1.season,  " +
                "  t1.year,  " +
                "  t1.season_id  " +
                "FROM  " +
                "  (SELECT  " +
                "    b.player_id,   " +
                "      p.first_name,   " +
                "      p.last_name,   " +
                "      s.season,   " +
                "      s.year,  " +
                "      g.season_id,  " +
                "      SUM(singles) + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4 + SUM(stolenbases) +SUM(runs) + SUM(rbis) + SUM(walks) + SUM(hitbypitch) - SUM(strikeouts) - SUM(caughtstealing) AS mvp     " +
                "  FROM   " +
                "    batting_stats b  " +
                "    JOIN players p ON b.player_id = p.id  " +
                "    JOIN games g ON b.game_id = g.id  " +
                "    JOIN seasons s ON g.season_id = s.id  " +
                "  GROUP BY  " +
                "    b.player_id,   " +
                "      p.first_name,   " +
                "      p.last_name,   " +
                "      s.season,   " +
                "      s.year,  " +
                "      g.season_id  " +
                "  ) t1   " +
                "JOIN  " +
                "  (SELECT  " +
                "    season_id, MAX(mvp) AS max_mvp  " +
                "  FROM (  " +
                "    SELECT b.player_id,   " +
                "      g.season_id, SUM(singles) + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4 + SUM(stolenbases) + SUM(runs) + SUM(rbis) + SUM(walks) + SUM(hitbypitch) - SUM(strikeouts) - SUM(caughtstealing) AS mvp  " +
                "      FROM batting_stats b  " +
                "      JOIN players p ON b.player_id = p.id  " +
                "      JOIN games g ON b.game_id = g.id  " +
                "      JOIN seasons s ON g.season_id = s.id  " +
                "    GROUP BY b.player_id, g.season_id  " +
                "    ) t2      " +
                "  GROUP BY  " +
                "    season_id  " +
                "  ) t3  " +
                "ON  " +
                "  t1.season_id = t3.season_id  " +
                "WHERE  " +
                "  t1.mvp = t3.max_mvp) t4  " +
                "GROUP BY   " +
                "  t4.player_id,  " +
                "    t4.first_name,  " +
                "    t4.last_name  " +
                "ORDER BY  " +
                "  4 DESC;  " +
                "      " +
                "      " +
                "/*  " +
                "SELECT   " +
                "  b.player_id,   " +
                "    p.first_name,   " +
                "    p.last_name,   " +
                "    s.season,   " +
                "    s.year,  " +
                "    SUM(singles) + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4 + SUM(stolenbases) +  " +
                "    SUM(runs) + SUM(rbis) + SUM(walks) + SUM(hitbypitch) - SUM(strikeouts) - SUM(caughtstealing) AS mvp  " +
                "FROM   " +
                "  batting_stats b  " +
                "JOIN  " +
                "  players p  " +
                "ON  " +
                "  b.player_id = p.id  " +
                "JOIN  " +
                "  games g  " +
                "ON  " +
                "  b.game_id = g.id  " +
                "JOIN  " +
                "  seasons s  " +
                "ON  " +
                "  g.season_id = s.id  " +
                "GROUP BY   " +
                "  b.player_id, p.first_name, p.last_name, s.season, s.year  " +
                "ORDER BY   " +
                "  6 DESC, s.year, p.last_name,p.first_name; */";
        return getTopPlayersAllTimeBattingForStat(q,"mvp");
    }

//    public List<RecordBean> getAllTimeBattingRecordMVP2(){
//        String q = "SELECT  " +
//                " t1.player_id, " +
//                " t1.first_name, " +
//                " t1.last_name, " +
//                " t1.season, " +
//                " t1.year, " +
//                " t1.season_id," +
//                " t1.mvp " +
//                "FROM " +
//                " (SELECT " +
//                "  b.player_id,  " +
//                "     p.first_name,  " +
//                "     p.last_name,  " +
//                "     s.season,  " +
//                "     s.year, " +
//                "     g.season_id, " +
//                "     SUM(singles) + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4 + SUM(stolenbases) +SUM(runs) + SUM(rbis) + SUM(walks) + SUM(hitbypitch) - SUM(strikeouts) - SUM(caughtstealing) AS mvp    " +
//                " FROM  " +
//                "  batting_stats b " +
//                "  JOIN players p ON b.player_id = p.id " +
//                "  JOIN games g ON b.game_id = g.id " +
//                "  JOIN seasons s ON g.season_id = s.id " +
//                " GROUP BY " +
//                "  b.player_id,  " +
//                "     p.first_name,  " +
//                "     p.last_name,  " +
//                "     s.season,  " +
//                "     s.year, " +
//                "     g.season_id " +
//                " ) t1  " +
//                "JOIN " +
//                " (SELECT " +
//                "  season_id, MAX(mvp) AS max_mvp " +
//                " FROM ( " +
//                "  SELECT b.player_id,  " +
//                "   g.season_id, SUM(singles) + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4 + SUM(stolenbases) + SUM(runs) + SUM(rbis) + SUM(walks) + SUM(hitbypitch) - SUM(strikeouts) - SUM(caughtstealing) AS mvp " +
//                "   FROM batting_stats b " +
//                "   JOIN players p ON b.player_id = p.id " +
//                "   JOIN games g ON b.game_id = g.id " +
//                "   JOIN seasons s ON g.season_id = s.id " +
//                "  GROUP BY b.player_id, g.season_id " +
//                "  ) t2     " +
//                " GROUP BY " +
//                "  season_id " +
//                " ) t3 " +
//                "ON " +
//                " t1.season_id = t3.season_id " +
//                "WHERE " +
//                " t1.mvp = t3.max_mvp  " +
//                "ORDER BY " +
//                " t1.year, t1.season DESC";
//
//        Query query = entityManager.createNativeQuery(q);
//        List<Object[]> allMVPRecordsecords = query.getResultList();
//        List<RecordBean> mvpList = new ArrayList<RecordBean>();
//
//        for(Object[] record:allMVPRecordsecords){
//            String recordString = (String)record[2] + ", " + (String)record[1];
//            BigDecimal tempRecord = (BigDecimal) record[6];
//
//            RecordBean tempBean = new RecordBean(recordString,"mvp",tempRecord);
//            mvpList.add(tempBean);
//        }
//        return mvpList;
//    }

}
