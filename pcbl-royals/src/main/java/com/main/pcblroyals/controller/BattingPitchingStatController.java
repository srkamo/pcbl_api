package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.BattingStatBean;
import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.service.BattingStatService;
import com.main.pcblroyals.service.GameService;
import com.main.pcblroyals.service.PitchingStatService;
import com.main.pcblroyals.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BattingPitchingStatController {

    @Autowired
    @Qualifier("battingStatService")
    private BattingStatService battingStatService;

    @Autowired
    @Qualifier("pitchingStatService")
    private PitchingStatService pitchingStatService;

    @Autowired
    @Qualifier("seasonService")
    private SeasonService seasonService;

    @Autowired
    @Qualifier("gameService")
    private GameService gameService;


    @GetMapping("api/getStatsBySeason/{id}")
    public List<Object> getStatsBySeason(@PathVariable(value = "id") int seasonId){
        List<Object> allStats = new ArrayList<>();
        // seasons for drop down
        allStats.add(seasonService.getAllSeasonReverseChronological());
        allStats.add(battingStatService.getBattingStatsBySeason(seasonId));
        allStats.add(pitchingStatService.getPitchingStatsBySeason(seasonId));

        return allStats;
    }

    @GetMapping("api/battingStatsBySeason/{id}")
    public List<BattingStatBean> getBattingStatsBySeason(@PathVariable(value = "id") int seasonId){
        return battingStatService.getBattingStatsBySeason(seasonId);
    }

    @GetMapping("api/pitchingStatsBySeason/{id}")
    public List<PitchingStatBean> getPitchingStatsBySeason(@PathVariable(value = "id") int seasonId){
        return pitchingStatService.getPitchingStatsBySeason(seasonId);
    }

    @GetMapping("api/getStatsBySeasonGame/{seasonId}/{gameId}")
    public List<Object> getStatsBySeasonGame(@PathVariable(value = "seasonId") int seasonId,
                                             @PathVariable(value = "gameId") int gameId){
        List<Object> allStats = new ArrayList<>();
        // seasons for drop down
        allStats.add(seasonService.getAllSeasonReverseChronological());
        // games for drop down
        allStats.add(gameService.getGamesBySeason(seasonId));

        // player batting stats by season and game
        allStats.add(battingStatService.getBattingStatsBySeasonGame(seasonId, gameId));
        allStats.add(pitchingStatService.getPitchingStatsBySeasonGame(seasonId, gameId));

        return allStats;
    }

}
