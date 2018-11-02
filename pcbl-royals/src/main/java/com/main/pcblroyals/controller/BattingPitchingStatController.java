package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.*;
import com.main.pcblroyals.data.Player;
import com.main.pcblroyals.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // single season page
    @GetMapping("api/getStatsBySeason/{id}")
    public Map<String, List<?>> getStatsBySeason(@PathVariable(value = "id") int seasonId){

        Map<String, List<?>> allStats = new HashMap<>();
        // seasons for drop down
        allStats.put("seasonsDropdown",seasonService.getAllSeasonReverseChronological());

        // batting stats (player)
        allStats.put("playerBatting",battingStatService.getBattingStatsBySeason(seasonId));
        // all time batting for this season
        allStats.put("totalBatting",battingStatService.getAllTimeBattingStatForSeason(seasonId));

        // pitching stats (player)
        allStats.put("playerPitching",pitchingStatService.getPitchingStatsBySeason(seasonId));
        // all time pitching for this season
        allStats.put("seasonPitching",pitchingStatService.getAllTimePitchingStatForSeason(seasonId));

        return allStats;
    }

//    @GetMapping("api/battingStatsBySeason/{id}")
//    public List<BattingStatPlayerBean> getBattingStatsBySeason(@PathVariable(value = "id") int seasonId){
//        return battingStatService.getBattingStatsBySeason(seasonId);
//    }
//
//    @GetMapping("api/pitchingStatsBySeason/{id}")
//    public List<PitchingStatPlayerBean> getPitchingStatsBySeason(@PathVariable(value = "id") int seasonId){
//        return pitchingStatService.getPitchingStatsBySeason(seasonId);
//    }

    // single game page
    @GetMapping("api/getStatsBySeasonGame/{seasonId}/{gameId}")
    public Map<String, List<?>>  getStatsBySeasonGame(@PathVariable(value = "seasonId") int seasonId,
                                             @PathVariable(value = "gameId") int gameId){

        Map<String, List<?>> allStats = new HashMap<>();
        // seasons for drop down
        allStats.put("seasonsDropdown",seasonService.getAllSeasonReverseChronological());

        // games for drop down
        allStats.put("gamesDropdown",gameService.getGamesBySeason(seasonId));

        // player batting stats by season and game
        allStats.put("playerBatting",battingStatService.getBattingStatsBySeasonGame(seasonId, gameId));
        // team stats for this game
        allStats.put("totalBatting",battingStatService.getTeamStatsForGame(seasonId, gameId));

        // player pitching stats by season and game
        allStats.put("playerPitching",pitchingStatService.getPitchingStatsBySeasonGame(seasonId, gameId));
        // all time records for this season pitching
        allStats.put("totalPitching",pitchingStatService.getTeamPitchingStatsForGame(seasonId, gameId));

        //game info
        allStats.put("gameInfo",gameService.getGameInfoById(gameId));

        return allStats;
    }

    // get just the team stat for a single game (for testing)
    @GetMapping("api/getTeamStatsForGame/{seasonId}/{gameId}")
    public List<BattingStatBean> getTeamStatsForGame(@PathVariable(value = "seasonId") int seasonId,
                                             @PathVariable(value = "gameId") int gameId) {


        return battingStatService.getTeamStatsForGame(seasonId, gameId);
    }

    // SINGLE PLAYER PAGE (all time)
    // player stats all time
    @GetMapping("api/getStatsSeasonByPlayer/{playerId}")
    public Map<String, List<?>> getBattingStatsSeasonByPlayer(@PathVariable(value = "playerId") int playerId){

        Map<String, List<?>> playerSeasonStats = new HashMap<>();

        // player season batting
        playerSeasonStats.put("seasonBatting", battingStatService.getBattingStatsSeasonByPlayer(playerId));
        // total batting stats player
        playerSeasonStats.put("allTimeBatting", battingStatService.getAllTimeBattingStatsForPlayer(playerId));

        // player season pitching
        playerSeasonStats.put("seasonPitching", pitchingStatService.getPitchingStatsSeasonByPlayer(playerId));
        //total pitching stats player
        playerSeasonStats.put("allTimePitching", pitchingStatService.getAllTimePitchingStatsForPlayer(playerId));

//        List<Object> playerSeasonStats = new ArrayList<>();
//        //player info
//        playerSeasonStats.add(playerService.getAllPlayer());
//
//        //player season batting
//        playerSeasonStats.add(battingStatService.getBattingStatsSeasonByPlayer(playerId));
//        //total batting stats player
//        playerSeasonStats.add(battingStatService.getAllTimeBattingStatsForPlayer(playerId));
//
//        //player season pitching
//        playerSeasonStats.add(pitchingStatService.getPitchingStatsBySeason(playerId));
//        //total pitching stats player
//        playerSeasonStats.add(pitchingStatService.getAllTimePitchingStatsForPlayer(playerId));

        return playerSeasonStats;
    }


    // SINGLE PLAYER PAGE (season)
    // player stats for single season (by game)
    @GetMapping("api/getStatsGameBySeasonPlayer/{seasonId}/{playerId}")
    public Map<String, List<?>> getBattingStatsGameBySeasonPlayer(@PathVariable(value = "seasonId") int seasonId,
                                                          @PathVariable(value = "playerId") int playerId){


        Map<String, List<?>> gameStats = new HashMap<>();

        // player game batting
        gameStats.put("gameBatting", battingStatService.getBattingStatsGameBySeasonPlayer(seasonId,playerId));
        // total batting stats player
        gameStats.put("totalBatting",battingStatService.getTotalBattingStatsForByPlayerBySeason(seasonId,playerId));

        // player game pitching
        gameStats.put("gamePitching", pitchingStatService.getPitchingStatsGameBySeasonPlayer(seasonId,playerId));
        //total pitching stats player
        // TODO: all time for season - pitchingStatService.getAllTimePitchingStatsForPlayerBySeason
        gameStats.put("totalPitching",pitchingStatService.getTotalPitchingStatsForPlayerBySeason(seasonId, playerId));

        //gameStats.put("seasonPitching", pitchingStatService.getAllTimePitchingStatsForPlayer(playerId));


//        List<Object> gameStats = new ArrayList<>();

//        //player info for drop down
//        gameStats.add(playerService.getAllPlayer());
//        // list of seasons that this player played in
//        gameStats.add(playerService.getPlayerById(playerId).getSeasons());
//        //game batting info, by season player
//        gameStats.add(battingStatService.getBattingStatsGameBySeasonPlayer(seasonId,playerId));
//        //game pitching info, by season player
//        gameStats.add(pitchingStatService.getPitchingStatsGameBySeasonPlayer(seasonId,playerId));

        return gameStats;
    }

    @GetMapping("api/getAllPlayers")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayer();
    }

}
