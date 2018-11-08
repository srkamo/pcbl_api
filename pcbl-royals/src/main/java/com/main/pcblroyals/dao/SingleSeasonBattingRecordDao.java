package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.SingleSeasonRecordBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Repository("singleSeasonBattingRecordDao")
public class SingleSeasonBattingRecordDao {

    @PersistenceContext
    EntityManager entityManager;

    protected int singleSeasonBattingGameFilter = 0;

    public List<SingleSeasonRecordBean> getTopPlayersSingleSeasonBattingForStat(String statQuery, String recordName){
        String q = statQuery;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allStatRecords = query.getResultList();

        //Selecting only first 4 names (plus tied people if needed)
        List<SingleSeasonRecordBean> topPlayersList = new ArrayList<SingleSeasonRecordBean>();
        BigDecimal lastRecordAdded = BigDecimal.valueOf(Integer.MIN_VALUE);
        int numPeopleAdded = 0;
        for(Object[] record:allStatRecords){
            String recordString = (String)record[2] + ", " + (String)record[1];
            String seasonString = (String)record[3] + " " + Integer.toString((int)record[4]);
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

    public String makeSingleSeasonBattingSumQuery(String recordName){
        String q = "SELECT  " +
                "    b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    SUM(" + recordName + ") AS " +  recordName +  " " +
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
        return q;
    }

    //hits single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordHits(){
        String q = "SELECT  " +
                "    b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    (SUM(singles) + SUM(doubles) + SUM(triples) + SUM(homeruns)) AS hits " +
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
        return getTopPlayersSingleSeasonBattingForStat(q,"hits");
    }

    //doubles single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordDoubles(){
        String recordName = "doubles";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"doubles");
    }

    //triples single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordTriples(){
        String recordName = "triples";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"triples");
    }

    //homeruns single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordHomeruns(){
        String recordName = "homeruns";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"homeruns");
    }

    //runs single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordRuns(){
        String recordName = "runs";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"runs");
    }

    //rbis single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordRbis(){
        String recordName = "rbis";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"rbis");
    }

    //stolenbases single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordStolenBases(){
        String recordName = "stolenbases";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"stolenbases");
    }

    //rbis single season record
    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordWalks(){
        String recordName = "walks";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"walks");
    }

    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordHitByPitch(){
        String recordName = "hitbypitch";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonBattingForStat(q,"hitbypitch");
    }

    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordBattingAverage(){
        String q = "SELECT  " +
                " b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    CASE WHEN " +
                "  SUM(atbats) = 0 THEN 0 " +
                "        ELSE ROUND(SUM(singles)/SUM(atbats),3) " +
                " END AS batting_average, " +
                " SUM(atbats), SUM(singles), COUNT(1) AS games " +
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
                "HAVING " +
                " COUNT(1) > " + Integer.toString(singleSeasonBattingGameFilter) + " " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";
        return getTopPlayersSingleSeasonBattingForStat(q,"batting_average");
    }

    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordSluggingPercentage(){
        String q = "SELECT  " +
                " b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    CASE WHEN" +
                "       SUM(atbats) = 0 THEN 0 " +
                "        ELSE ROUND((SUM(singles)*1 + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4)/SUM(atbats),3) " +
                "    END AS slugging_percentage, " +
                "    SUM(atbats), SUM(singles), SUM(doubles), SUM(triples), SUM(homeruns), COUNT(1) AS games " +
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
                "HAVING " +
                " COUNT(1) > " + Integer.toString(singleSeasonBattingGameFilter) + " " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";
        return getTopPlayersSingleSeasonBattingForStat(q,"slugging_percentage");
    }

    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordOnBasePercentage(){
        String q = "SELECT  " +
                " b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    s.season,  " +
                "    s.year, " +
                "    CASE WHEN  " +
                "    SUM(atbats) + SUM(walks) + SUM(hitbypitch) + SUM(sacrifices) = 0 THEN 0 " +
                "        ELSE ROUND((SUM(singles) + SUM(walks) + SUM(hitbypitch))/(SUM(atbats) + SUM(walks) + SUM(hitbypitch) + SUM(sacrifices)),3)  " +
                "  END AS on_base_percentage,  " +
                "  SUM(atbats), SUM(singles), SUM(walks), SUM(hitbypitch), SUM(sacrifices), COUNT(1) AS games " +
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
                "HAVING " +
                " COUNT(1) > " + Integer.toString(singleSeasonBattingGameFilter) + " " +
                "ORDER BY  " +
                " 6 DESC, s.year, p.last_name, p.first_name";

        return getTopPlayersSingleSeasonBattingForStat(q,"on_base_percentage");
    }

    public List<SingleSeasonRecordBean> getSingleSeasonBattingRecordMVP(){
        String q = "SELECT  " +
                " t1.player_id, " +
                " t1.first_name, " +
                " t1.last_name, " +
                " t1.season, " +
                " t1.year, " +
                " t1.season_id," +
                " t1.mvp " +
                "FROM " +
                " (SELECT " +
                "  b.player_id,  " +
                "     p.first_name,  " +
                "     p.last_name,  " +
                "     s.season,  " +
                "     s.year, " +
                "     g.season_id, " +
                "     SUM(singles) + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4 + SUM(stolenbases) +SUM(runs) + SUM(rbis) + SUM(walks) + SUM(hitbypitch) - SUM(strikeouts) - SUM(caughtstealing) AS mvp    " +
                " FROM  " +
                "  batting_stats b " +
                "  JOIN players p ON b.player_id = p.id " +
                "  JOIN games g ON b.game_id = g.id " +
                "  JOIN seasons s ON g.season_id = s.id " +
                " GROUP BY " +
                "  b.player_id,  " +
                "     p.first_name,  " +
                "     p.last_name,  " +
                "     s.season,  " +
                "     s.year, " +
                "     g.season_id " +
                " ) t1  " +
                "JOIN " +
                " (SELECT " +
                "  season_id, MAX(mvp) AS max_mvp " +
                " FROM ( " +
                "  SELECT b.player_id,  " +
                "   g.season_id, SUM(singles) + SUM(doubles)*2 + SUM(triples)*3 + SUM(homeruns)*4 + SUM(stolenbases) + SUM(runs) + SUM(rbis) + SUM(walks) + SUM(hitbypitch) - SUM(strikeouts) - SUM(caughtstealing) AS mvp " +
                "   FROM batting_stats b " +
                "   JOIN players p ON b.player_id = p.id " +
                "   JOIN games g ON b.game_id = g.id " +
                "   JOIN seasons s ON g.season_id = s.id " +
                "  GROUP BY b.player_id, g.season_id " +
                "  ) t2     " +
                " GROUP BY " +
                "  season_id " +
                " ) t3 " +
                "ON " +
                " t1.season_id = t3.season_id " +
                "WHERE " +
                " t1.mvp = t3.max_mvp  " +
                "ORDER BY " +
                " t1.year, t1.season DESC";

        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allMVPRecordsecords = query.getResultList();
        List<SingleSeasonRecordBean> mvpList = new ArrayList<SingleSeasonRecordBean>();

        for(Object[] record:allMVPRecordsecords){
            String recordString = (String)record[2] + ", " + (String)record[1];
            String seasonString = (String)record[3] + " " + Integer.toString((int)record[4]);
            BigDecimal tempRecord = (BigDecimal) record[6];

            SingleSeasonRecordBean tempBean = new SingleSeasonRecordBean(recordString,"mvp",tempRecord,seasonString);
            mvpList.add(tempBean);
        }
        return mvpList;
    }
}
