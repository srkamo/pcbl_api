package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.*;
import com.main.pcblroyals.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    @Qualifier("playerService")
    private PlayerService playerService;

    @Autowired
    @Qualifier("seasonService")
    private SeasonService seasonService;

    @Autowired
    @Qualifier("gameService")
    private GameService gameService;

    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;

    @Autowired
    @Qualifier("battingStatService")
    private BattingStatService battingStatService;

    @Autowired
    @Qualifier("pitchingStatService")
    private PitchingStatService pitchingStatService;

    @PostMapping("/api/createPlayer")
    public void createNewPlayer(@RequestBody @Valid Player player) {
        playerService.addPlayer(player);
    }

    @PostMapping("/api/createSeason")
    public void createNewSeason(@RequestBody @Valid Season season) {
        seasonService.addSeason(season);
    }

    @PostMapping("/api/createGame")
    public void createNewGame(@RequestBody @Valid Game game) {gameService.addGame(game); }

    @PostMapping("/api/createTeam")
    public void createNewTeam(@RequestBody @Valid Team team) {teamService.addTeam(team);}

    @PostMapping("/api/createBattingStat")
    public void createNewBattingStat(@RequestBody @Valid BattingStat battingStat) {battingStatService.addBattingStat(battingStat);}

    @PostMapping("/api/createPitchingStat")
    public void createNewPitchingStat(@RequestBody @Valid PitchingStat pitchingStat) {pitchingStatService.addPitchingStat(pitchingStat);}

}
