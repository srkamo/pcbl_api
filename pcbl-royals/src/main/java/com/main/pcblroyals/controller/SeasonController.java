package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.AllTimeSeasonBean;
import com.main.pcblroyals.data.Game;
import com.main.pcblroyals.data.Season;
import com.main.pcblroyals.service.GameService;
import com.main.pcblroyals.service.PlayerService;
import com.main.pcblroyals.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SeasonController {

    @Autowired
    @Qualifier("seasonService")
    private SeasonService seasonService;

    @Autowired
    @Qualifier("gameService")
    private GameService gameService;

    @Autowired
    @Qualifier("playerService")
    private PlayerService playerService;

    @PostMapping("/api/createSeason")
    public void createNewSeason(@Valid @RequestBody Season season) {
        seasonService.addSeason(season);
    }

    @PutMapping("/api/changeSeason")
    public void changeExistingSeason(@Valid @RequestBody Season season) {
        seasonService.modifySeason(season);
    }

    @DeleteMapping("/api/removeSeason/{id}")
    public void removeSeason(@PathVariable(value = "id") int seasonId) {
        seasonService.removeSeason(seasonId);
    }

    @GetMapping("/api/viewSingleSeason/{id}")
    public Season viewSeasonById(@PathVariable(value = "id") int seasonId) {
        return seasonService.getSeasonById(seasonId);
    }

    @GetMapping("/api/getAllSeasonsAndRecentGames")
    public List<Object> getMostRecentGames() {
        List<Object> seasonsAndRecentGames = new ArrayList<Object>();

        // all season records
        seasonsAndRecentGames.add(seasonService.getAllSeason());
        // all time for all seasons
        seasonsAndRecentGames.add(seasonService.getAllTimeRecord());
        // get the 3 most recent games
        seasonsAndRecentGames.add(gameService.getMostRecentGames());

        return seasonsAndRecentGames;
    }

    @GetMapping("/api/viewAllSeasons")
    public List<Season> viewAllSeasons() {
        return seasonService.getAllSeasonReverseChronological();
    }

    @GetMapping("/api/getGamesBySeason/{seasonId}")
    public List<Game> getGamesBySeason(@PathVariable(value = "seasonId") int seasonId) {
        return gameService.getGamesBySeason(seasonId);
    }

    // gets the w-l-t across all seasons
    @GetMapping("/api/getAllTimeRecord")
    public List<AllTimeSeasonBean> getAllTimeRecord() {
        return seasonService.getAllTimeRecord();
    }


    @GetMapping("/api/getAllSeasonsForPlayer/{playerId}")
    public List<Season> getAllSeasonsForPlayer(@PathVariable(value = "playerId") int playerId) {
        return playerService.getPlayerById(playerId).getSeasons();
    }

}