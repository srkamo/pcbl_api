package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.Season;
import com.main.pcblroyals.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class SeasonController {

    @Autowired
    @Qualifier("seasonService")
    private SeasonService seasonService;

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

    @GetMapping("/api/viewAllSeason")
    public List<Season> viewAllseason() {
        return seasonService.getAllSeason();
    }


}