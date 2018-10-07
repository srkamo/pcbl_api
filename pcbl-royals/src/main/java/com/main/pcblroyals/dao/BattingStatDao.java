package com.main.pcblroyals.dao;

import com.main.pcblroyals.data.BattingStat;
import com.main.pcblroyals.data.SeasonBattingStat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("battingStatDao")
public class BattingStatDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<BattingStat> getSeasonStats(int seasonId){

        /*
        select player_id, sum(errors) as errors, sum(atbats) as atbats, sum(caughtstealing) as caughtstealing, sum(doubles) as doubles,
            sum(fielderschoices) as fielderschoices, sum(hitbypitch) as hitbypitch, sum(homeruns) as homeruns,
            sum(passedballs) as passedballs, sum(rbis) as rbis, sum(runs) as runs, sum(sacrifices) as sacrifices, sum(singles) as singles,
            sum(stolenbases) as stolenbases, sum(strikeouts) as strikeouts, sum(triples) as triples, sum(walks) as walks
        from batting_stats where game_id in (select id from games where season_id  = 1) group by player_id;
         */

//        String q = "select b.playerId, count(1) as games, sum(errors) as errors, sum(atbats) as atbats, "
//            + "sum(caughtstealing) as caughtstealing, sum(doubles) as doubles, sum(fielderschoices) as fielderschoices, "
//            + "sum(hitbypitch) as hitbypitch, sum(homeruns) as homeruns, sum(passedballs) as passedballs, "
//            + "sum(rbis) as rbis, sum(runs) as runs, sum(sacrifices) as sacrifices, sum(singles) as singles, "
//            + "sum(stolenbases) as stolenbases, sum(strikeouts) as strikeouts, sum(triples) as triples, sum(walks) as walks "
//            + "from batting_stats where game_id in (select id from games where season_id  = " + seasonId
//                + ") group by player_id";

        String q = "select b from batting_stats b where game_id in (select id from games where season_id  = " + seasonId + ")";

//        String q = "select playerId, count(1) as games, sum(atBats) as atbats, sum(singles) as singles, sum(doubles) as doubles, " +
//                "sum(triples) as triples, sum(homeRuns) as homeruns, sum(walks) as walks, sum(hitByPitch) as hitbypitch, " +
//                "sum(sacrifices) as sacrifices, sum(runs) as runs, sum(rbis) as rbis, sum(stolenBases) as stolenbases, " +
//                "sum(passedBalls) as passedballs, sum(caughtStealing) as caughtstealing, sum(strikeOuts) as strikeouts " +
//                "from season_batting_stats b where b.seasonId = 1 group by player_id";

//        String q = "select playerId, count(1), sum(atBats), sum(singles), sum(doubles), " +
//                "sum(triples), sum(homeRuns), sum(walks), sum(hitByPitch), " +
//                "sum(sacrifices), sum(runs), sum(rbis), sum(stolenBases), " +
//                "sum(passedBalls), sum(caughtStealing), sum(strikeOuts) " +
//                "from season_batting_stats b where b.seasonId = 1 group by player_id";

        Query query = entityManager.createQuery(q);
        return (List<BattingStat>) query.getResultList();
    }
}
