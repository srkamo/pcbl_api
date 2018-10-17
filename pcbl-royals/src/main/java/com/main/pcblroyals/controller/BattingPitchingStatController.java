package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.*;
import com.main.pcblroyals.service.*;
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

    @Autowired
    @Qualifier("playerService")
    private PlayerService playerService;


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
    public List<BattingStatPlayerBean> getBattingStatsBySeason(@PathVariable(value = "id") int seasonId){
        return battingStatService.getBattingStatsBySeason(seasonId);
    }

    @GetMapping("api/pitchingStatsBySeason/{id}")
    public List<PitchingStatPlayerBean> getPitchingStatsBySeason(@PathVariable(value = "id") int seasonId){
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

    @GetMapping("api/getStatsSeasonByPlayer/{playerId}")
    public List<Object> getBattingStatsSeasonByPlayer(@PathVariable(value = "playerId") int playerId){

        List<Object> playerSeasonStats = new ArrayList<>();
        //player info
        playerSeasonStats.add(playerService.getPlayerById(playerId));
        //player season batting
        playerSeasonStats.add(battingStatService.getBattingStatsSeasonByPlayer(playerId));
        //player season pitching
        playerSeasonStats.add(pitchingStatService.getPitchingStatsBySeason(playerId));

        return playerSeasonStats;
    }

    @GetMapping("api/getStatsGameBySeasonPlayer/{seasonId}/{playerId}")
    public List<Object> getBattingStatsGameBySeasonPlayer(@PathVariable(value = "seasonId") int seasonId,
                                                                       @PathVariable(value = "playerId") int playerId){
        List<Object> gameStats = new ArrayList<>();

        //player info
        gameStats.add(playerService.getPlayerById(playerId));
        //game batting info, by season player
        gameStats.add(battingStatService.getBattingStatsGameBySeasonPlayer(seasonId,playerId));
        //game pitching info, by season player
        gameStats.add(pitchingStatService.getPitchingStatsGameBySeasonPlayer(seasonId,playerId));

        return gameStats;
    }

}
