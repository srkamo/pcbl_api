package com.main.pcblroyals.dao;

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
}
