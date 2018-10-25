package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.SingleGameRecordBean;
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
@Repository("singleGamePitchingRecordDao")
public class SingleGamePitchingRecordDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<SingleGameRecordBean> getTopPlayersSingleGamePitchingForStat(String statQuery, String recordName){
        String q = statQuery;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allStatRecords = query.getResultList();

        //Selecting only first 4 names (plus tied people if needed)
        List<SingleGameRecordBean> topPlayersList = new ArrayList<SingleGameRecordBean>();
        BigDecimal lastRecordAdded = BigDecimal.valueOf(Integer.MIN_VALUE);
        int numPeopleAdded = 0;
        for(Object[] record:allStatRecords){
            String recordString = (String)record[2] + ", " + (String)record[1];

            String gameString = "";
            int homeAway = (int)record[4];
            String opponent = (String)record[3];
            String date = (String)record[7];

            if(homeAway == 1){
                gameString = date + " v.s " +  opponent;
            }
            else {
                gameString = date + " @ " +  opponent;
            }

            BigDecimal tempRecord = (BigDecimal) record[8];
            if(numPeopleAdded >= 5 && !tempRecord.equals(lastRecordAdded)){
                break;
            } else {
                SingleGameRecordBean tempBean = new SingleGameRecordBean(recordString,recordName,tempRecord,gameString);
                topPlayersList.add(tempBean);
            }
            lastRecordAdded = tempRecord;
            numPeopleAdded += 1;
        }
        return topPlayersList;
    }

    public String makeSingleGamePitchingSumQuery(String recordName){
        String q = "SELECT  " +
                " ps.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    t.name, " +
                "    g.home_team,  " +
                "    s.season,  " +
                "    s.year," +
                "    DATE_FORMAT(g.date,'%m/%d/%Y') as date, " +
                "    SUM(" + recordName + ") AS" + recordName +  "hits " +
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
                "  teams t " +
                "ON " +
                "  g.opponent_team_id = t.id " +
                "JOIN " +
                "  seasons s " +
                "ON " +
                "  g.season_id = s.id " +
                "GROUP BY  " +
                "  ps.player_id, p.first_name, p.last_name, t.name, g.home_team, s.season, s.year, g.date " +
                "ORDER BY  " +
                "  9 DESC, g.date, p.last_name,p.first_name";
        return q;
    }

    public List<SingleGameRecordBean> getSingleGamePitchingRecordStrikeouts(){
        String recordName = "strikeouts";
        String q = makeSingleGamePitchingSumQuery(recordName);
        return getTopPlayersSingleGamePitchingForStat(q,"strikeouts");
    }
}
