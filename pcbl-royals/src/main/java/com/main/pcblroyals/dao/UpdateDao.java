package com.main.pcblroyals.dao;

import com.main.pcblroyals.data.BattingStat;
import com.main.pcblroyals.data.PitchingStat;
import com.main.pcblroyals.data.Player;
import com.main.pcblroyals.data.Season;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by rblay on 11/11/18.
 */
@Repository("updateDao")
public class UpdateDao {

    @PersistenceContext
    EntityManager entityManager;

    public void addSeasonPlayerIfNotExists(int seasonId, int playerId){
        String q = " INSERT INTO player_season (player_id,season_id) " +
                " SELECT * FROM (SELECT " + playerId + " , " + seasonId +" ) AS tmp " +
                " WHERE NOT EXISTS ( " +
                "    SELECT player_id,season_id " +
                "    FROM player_season " +
                "    WHERE player_id = " + playerId +
                "    AND season_id =" + seasonId +
                " ) LIMIT 1;";
        Query query = entityManager.createNativeQuery(q);
        query.executeUpdate();
    }

    public void addSeasonTeamIfNotExists(int seasonId, int teamId){
        String q = " INSERT INTO team_season (team_id,season_id) " +
                " SELECT * FROM (SELECT " + teamId + " , " + seasonId +" ) AS tmp " +
                " WHERE NOT EXISTS ( " +
                "    SELECT team_id,season_id " +
                "    FROM team_season " +
                "    WHERE team_id = " + teamId +
                "    AND season_id =" + seasonId +
                " ) LIMIT 1;";
        Query query = entityManager.createNativeQuery(q);
        query.executeUpdate();
    }

    public void updatePlayer(Player player){
        entityManager.merge(player);
    }

    public void updateSeason(Season season){
        entityManager.merge(season);
    }

    public void updateBattingStat(BattingStat battingStat){
        entityManager.merge(battingStat);
    }

    public void updatePitchingStat(PitchingStat pitchingStat){
        entityManager.merge(pitchingStat);
    }
}
