package com.main.pcblroyals.dao;

import com.main.pcblroyals.data.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("teamDao")
public class TeamDao {

    @PersistenceContext
    EntityManager entityManager;

    public void insertGame(Team team){ entityManager.persist(team);}
}
