package com.main.pcblroyals.dao;

import com.main.pcblroyals.bean.GameBean;
import com.main.pcblroyals.data.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("gameDao")
public class GameDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<Game> getAllGames() {
        String q = "select g from games g order by g.date desc";
        Query query = entityManager.createQuery(q);
        return (List<Game>) query.getResultList();
    }

    public List<Game> getGamesBySeason(int seasonId){
        String q = "select g from games g where g.season.id = " + seasonId + "order by g.date desc";
        Query query = entityManager.createQuery(q);
        return (List<Game>) query.getResultList();
    }

    public List<GameBean> getGameInfoById(int gameId){
        String q = "select new com.main.pcblroyals.bean.GameBean" +
                "(g.id, g.opponent.id, g.opponent.name, g.homeTeam, g.date) " +
                "from games g " +
                "where g.id = " + gameId;

        Query query = entityManager.createQuery(q);
        return (List<GameBean>) query.getResultList();
    }

    public void insertGame(Game game){ entityManager.persist(game);}
}
