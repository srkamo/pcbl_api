package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.SingleSeasonBattingRecordBean;
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
@Repository("recordDao")
public class RecordDao {

    @PersistenceContext
    EntityManager entityManager;

    protected int singleSeasonBattingGameFilter = 10;

    //SINGLE SEASON RECORDS BATTING
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
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHits(){
        String recordName = "singles";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"hits");
    }

    //doubles single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordDoubles(){
        String recordName = "doubles";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"doubles");
    }

    //triples single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordTriples(){
        String recordName = "triples";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"triples");
    }

    //homeruns single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHomeruns(){
        String recordName = "homeruns";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"homeruns");
    }

    //runs single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordRuns(){
        String recordName = "runs";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"runs");
    }

    //rbis single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordRbis(){
        String recordName = "rbis";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"rbis");
    }

    //stolenbases single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordStolenBases(){
        String recordName = "stolenbases";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"stolenbases");
    }

    //rbis single season record
    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordWalks(){
        String recordName = "walks";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"walks");
    }

    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordHitByPitch(){
        String recordName = "hitbypitch";
        String q = makeSingleSeasonBattingSumQuery(recordName);
        return getTopPlayersSingleSeasonForStat(q,"hitbypitch");
    }

    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordBattingAverage(){
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
        return getTopPlayersSingleSeasonForStat(q,"batting average");
    }

    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordSluggingPercentage(){
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
        return getTopPlayersSingleSeasonForStat(q,"slugging percentage");
    }

    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordOnBasePercentage(){
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

        return getTopPlayersSingleSeasonForStat(q,"on base percentage");
    }

    public List<SingleSeasonBattingRecordBean> getSingleSeasonBattingRecordMVP(){
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
                " t1.year, t1.season DESC;";

        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allMVPRecordsecords = query.getResultList();
        List<SingleSeasonBattingRecordBean> mvpList = new ArrayList<SingleSeasonBattingRecordBean>();

        for(Object[] record:allMVPRecordsecords){
            String recordString = (String)record[2] + ", " + (String)record[1];
            String seasonString = (String)record[3] + " " + Integer.toString((int)record[4]);
            BigDecimal tempRecord = (BigDecimal) record[6];

            SingleSeasonBattingRecordBean tempBean = new SingleSeasonBattingRecordBean(recordString,seasonString,"mvp",tempRecord);
            mvpList.add(tempBean);
        }
        return mvpList;
    }
}
