package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.Team;
import com.main.pcblroyals.service.GameService;
import com.main.pcblroyals.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;

    @GetMapping("api/getAllTeams")
    public List<Team> getAllTeams(){
        return teamService.getAllTeam();
    }
}
