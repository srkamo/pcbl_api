package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.SeasonPlayerBean;
import com.main.pcblroyals.bean.SeasonTeamBean;
import com.main.pcblroyals.dao.UpdateDao;
import com.main.pcblroyals.data.*;
import com.main.pcblroyals.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @Autowired
    @Qualifier("updateService")
    private UpdateService updateService;

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

    @PostMapping("/api/updateSeasonPlayer")
    public void updateSeasonPlayer(@RequestBody @Valid SeasonPlayerBean seasonPlayerBean){ updateService.addSeasonPlayerIfNotExists(seasonPlayerBean);}

    @PostMapping("/api/updateSeasonTeam")
    public void updateSeasonTeam(@RequestBody @Valid SeasonTeamBean seasonTeamBean){ updateService.addSeasonTeamIfNotExists(seasonTeamBean);}

    @PostMapping("/api/updatePlayer")
    public void updatePlayer(@RequestBody @Valid Player player){ updateService.updatePlayer(player);}

    @PostMapping("/api/updateSeason")
    public void updateSeason(@RequestBody @Valid Season season) {
        updateService.updateSeason(season);
    }

    @PostMapping("/api/updateBattingStat")
    public void updateBattingStat(@RequestBody @Valid BattingStat battingStat) {updateService.updateBattingStat(battingStat);}

    @PostMapping("/api/updatePitchingStat")
    public void updatePitchingStat(@RequestBody @Valid PitchingStat pitchingStat) {
        System.out.println("EHRE");
        updateService.updatePitchingStat(pitchingStat);}

}
