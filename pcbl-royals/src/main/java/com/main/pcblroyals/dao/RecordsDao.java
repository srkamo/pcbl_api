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
@Repository("recordsDao")
public class RecordsDao {

    @PersistenceContext
    EntityManager entityManager;

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
