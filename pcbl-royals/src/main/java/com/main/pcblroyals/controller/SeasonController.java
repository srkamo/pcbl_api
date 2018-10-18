package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.AllTimeSeasonBean;
import com.main.pcblroyals.data.Game;
import com.main.pcblroyals.data.Season;
import com.main.pcblroyals.service.GameService;
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
        seasonsAndRecentGames.add(seasonService.getAllSeason());
        seasonsAndRecentGames.add(seasonService.getAllTimeRecord());
        seasonsAndRecentGames.add(gameService.getMostRecentGames());

        return seasonsAndRecentGames;
    }

    @GetMapping("/api/viewAllSeason")
    public List<Season> viewAllseason() {
        return seasonService.getAllSeason();
    }

    // gets the w-l-t across all seasons
    @GetMapping("/api/getAllTimeRecord")
    public List<AllTimeSeasonBean> getAllTimeRecord() {
        return seasonService.getAllTimeRecord();
    }


}