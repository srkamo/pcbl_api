package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.bean.PitchingStatPlayerBean;
import com.main.pcblroyals.data.CareerBattingStat;
import com.main.pcblroyals.data.CareerPitchingStat;
import com.main.pcblroyals.data.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("careerPitchingStatDao")
public class CareerPitchingStatDao {

    @PersistenceContext
    EntityManager entityManager;

//    public Player selectPlayerById(int playerId) {
//        return entityManager.find(Player.class, playerId);
//    }
//
//    public void insertPlayer(Player player) {
//        entityManager.persist(player);
//    }
//
//    public void updatePlayer(Player player) {
//
//        Player playerToUpdate = selectPlayerById(player.getId());
//
//        playerToUpdate.setFirstName(player.getFirstName());
//        playerToUpdate.setLastName(player.getFirstName());
//        playerToUpdate.setJerseyNumber(player.getJerseyNumber());
//        playerToUpdate.setPositions(player.getPositions());
//        playerToUpdate.setThrowSide(player.getThrowSide());
//        playerToUpdate.setBatSide(player.getBatSide());
//        playerToUpdate.setSeasons(player.getSeasons());
//        entityManager.flush();
//    }
//
//    public void deletePlayer(int playerId) {
//        entityManager.remove(selectPlayerById(playerId));
//    }
//
//    public List<Player> selectAllPlayer() {
//        String q = "select * from players";
//        Query query = entityManager.createQuery(q);
//        return (List<Player>) query.getResultList();
//    }

//    public List<CareerPitchingStat> selectAllCareerPitchingStats(){
//        String q = "select p from pitching_career_stats p order by p.player.lastName";
//        Query query = entityManager.createQuery(q);
//        return (List<CareerPitchingStat>) query.getResultList();
//    }

    public List<PitchingStatPlayerBean> selectAllCareerPitchingStats(){
        String q = "select new com.main.pcblroyals.bean.PitchingStatPlayerBean(" +
                "p.player.id, p.player.firstName, p.player.lastName, games, " +
                "wins, " +
                "losses, " +
                "ties, " +
                "saves, " +
                "innings, " +
                "earnedRuns," +
                "totalRuns, " +
                "strikeouts, " +
                "walks, " +
                "hitByPitch, " +
                "hits, " +
                "wildPitches, " +
                "stolenBases, " +
                "pickoffs " +
                ") " +
                "from pitching_career_stats p " +
                " order by p.player.lastName, p.player.firstName";
        Query query = entityManager.createQuery(q);
        return (List<PitchingStatPlayerBean>) query.getResultList();
    }

}
