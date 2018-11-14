package com.main.pcblroyals.service;

import com.main.pcblroyals.dao.TeamDao;
import com.main.pcblroyals.data.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("teamService")
public class TeamService {

    @Autowired
    @Qualifier("teamDao")
    TeamDao teamDao;

    @Transactional
    public void addTeam(Team team){ teamDao.insertGame(team);}

    @Transactional
    public List<Team> getAllTeam() {
        return teamDao.selectAllTeams();
    }
}
