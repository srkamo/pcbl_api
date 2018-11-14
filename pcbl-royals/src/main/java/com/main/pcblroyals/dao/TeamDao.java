package com.main.pcblroyals.dao;

import com.main.pcblroyals.data.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("teamDao")
public class TeamDao {

    @PersistenceContext
    EntityManager entityManager;

    public void insertGame(Team team){ entityManager.persist(team);}

    public List<Team> selectAllTeams() {
        String q = "select t from teams t";
        Query query = entityManager.createQuery(q);
        return (List<Team>) query.getResultList();
    }
}
