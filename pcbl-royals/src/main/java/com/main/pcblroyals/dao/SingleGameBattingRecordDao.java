package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.SingleGameRecordBean;
import com.main.pcblroyals.bean.SingleSeasonRecordBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rblay on 10/24/18.
 */
@Repository("singleGameBattingRecordDao")
public class SingleGameBattingRecordDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<SingleGameRecordBean> getTopPlayersSingleGameBattingForStat(String statQuery, String recordName){
        String q = statQuery;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> allStatRecords = query.getResultList();

        //Selecting only first 4 names (plus tied people if needed)
        List<SingleGameRecordBean> topPlayersList = new ArrayList<SingleGameRecordBean>();
        BigDecimal lastRecordAdded = BigDecimal.valueOf(Integer.MIN_VALUE);
        int numPeopleAdded = 0;
        for(Object[] record:allStatRecords){
            int player_id = (int)record[0];
            String recordString = (String)record[2] + ", " + (String)record[1];

            int homeAway = (int)record[4];
            String date = (String)record[7];
            String opponent = (String)record[3];
            String gameString = "";

            if(homeAway == 1){
                gameString = date + " v.s " +  opponent;
            }
            else {
                gameString = date + " @ " +  opponent;
            }

            BigDecimal tempRecord = (BigDecimal) record[8];
            DecimalFormat df = new DecimalFormat("0");
            String recordValueString = df.format(tempRecord);

            if(numPeopleAdded >= 5 && !tempRecord.equals(lastRecordAdded)){
                break;
            } else {
                SingleGameRecordBean tempBean = new SingleGameRecordBean(recordString,recordName,recordValueString,gameString,player_id);
                topPlayersList.add(tempBean);
            }
            lastRecordAdded = tempRecord;
            numPeopleAdded += 1;
        }
        return topPlayersList;
    }

    public String makeSingleGameBattingSumQuery(String recordName){
        String q = "SELECT  " +
                " b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    t.name, " +
                "    g.home_team,  " +
                "    s.season,  " +
                "    s.year, " +
                "    DATE_FORMAT(g.date,'%m/%d/%Y') as date, " +
                "    SUM(" + recordName + ") AS" + recordName +  "hits " +
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
                " teams t " +
                "ON " +
                " g.opponent_team_id = t.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " b.player_id, p.first_name, p.last_name, t.name, g.home_team, s.season, s.year, g.date " +
                "ORDER BY  " +
                " 9 DESC, g.date, p.last_name,p.first_name";
        return q;
    }


    public List<SingleGameRecordBean> getSingleGameBattingRecordHits(){
        String q = "SELECT  " +
                " b.player_id,  " +
                "    p.first_name,  " +
                "    p.last_name,  " +
                "    t.name, " +
                "    g.home_team,  " +
                "    s.season,  " +
                "    s.year, " +
                "    DATE_FORMAT(g.date,'%m/%d/%Y') as date, " +
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
                " teams t " +
                "ON " +
                " g.opponent_team_id = t.id " +
                "JOIN " +
                " seasons s " +
                "ON " +
                " g.season_id = s.id " +
                "GROUP BY  " +
                " b.player_id, p.first_name, p.last_name, t.name, g.home_team, s.season, s.year, g.date " +
                "ORDER BY  " +
                " 9 DESC, g.date, p.last_name,p.first_name";
        return getTopPlayersSingleGameBattingForStat(q,"hits");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordDoubles(){
        String recordName = "doubles";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"doubles");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordTriples(){
        String recordName = "triples";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"triples");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordHomeruns(){
        String recordName = "homeruns";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"homeruns");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordRuns(){
        String recordName = "runs";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"runs");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordRbis(){
        String recordName = "rbis";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"rbis");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordStolenBases(){
        String recordName = "stolenbases";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"stolenbases");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordWalks(){
        String recordName = "walks";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"walks");
    }

    public List<SingleGameRecordBean> getSingleGameBattingRecordHitByPitch(){
        String recordName = "hitbypitch";
        String q = makeSingleGameBattingSumQuery(recordName);
        return getTopPlayersSingleGameBattingForStat(q,"hitbypitch");
    }

}
