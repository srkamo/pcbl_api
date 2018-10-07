package com.main.pcblroyals.dao;

import com.main.pcblroyals.data.CareerBattingStat;
import com.main.pcblroyals.data.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("careerBattingStatDao")
public class CareerBattingStatDao {

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

    public List<CareerBattingStat> selectAllCareerStats(){
        String q = "select b from batting_career_stats b";
        Query query = entityManager.createQuery(q);
        return (List<CareerBattingStat>) query.getResultList();
    }

}
