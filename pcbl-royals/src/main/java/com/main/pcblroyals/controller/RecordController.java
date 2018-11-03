package com.main.pcblroyals.controller;

import com.main.pcblroyals.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rblay on 10/24/18.
 */
@RestController
@CrossOrigin
public class RecordController {

    @Autowired
    @Qualifier("singleSeasonBattingRecordService")
    private SingleSeasonBattingRecordService singleSeasonBattingRecordService;

    @Autowired
    @Qualifier("singleSeasonPitchingRecordService")
    private SingleSeasonPitchingRecordService singleSeasonPitchingRecordService;

    @Autowired
    @Qualifier("singleGameBattingRecordService")
    private SingleGameBattingRecordService singleGameBattingRecordService;

    @Autowired
    @Qualifier("singleGamePitchingRecordService")
    private SingleGamePitchingRecordService singleGamePitchingRecordService;

    @Autowired
    @Qualifier("allTimeBattingRecordService")
    private AllTimeBattingRecordService allTimeBattingRecordService;

    @Autowired
    @Qualifier("allTimePitchingRecordService")
    private AllTimePitchingRecordService allTimePitchingRecordService;

    @GetMapping("api/getAllTimeRecordsBatting")
    public Map<String,Object> getAllTimeRecordsBatting(){
        Map<String,Object> recordsMap = new HashMap<String, Object>();

        /////////////////////
        //ALL TIME BATTING
        /////////////////////
        //all time batting records games
        recordsMap.put("all_time_games",allTimeBattingRecordService.getAllTimeBattingRecordGames());

        //all time batting records hits
        recordsMap.put("all_time_hits",allTimeBattingRecordService.getAllTimeBattingRecordHits());

        //all time batting records doubles
        recordsMap.put("all_time_doubles",allTimeBattingRecordService.getAllTimeBattingRecordDoubles());

        //all time batting records triples
        recordsMap.put("all_time_triples",allTimeBattingRecordService.getAllTimeBattingRecordTriples());

        //all time batting records homeruns
        recordsMap.put("all_time_homeruns",allTimeBattingRecordService.getAllTimeBattingRecordHomeruns());

        //all time batting records runs
        recordsMap.put("all_time_runs",allTimeBattingRecordService.getAllTimeBattingRecordRuns());

        //all time batting records rbis
        recordsMap.put("all_time_rbis",allTimeBattingRecordService.getAllTimeBattingRecordRbis());

        //all time batting records stolenbases
        recordsMap.put("all_time_stolenbases",allTimeBattingRecordService.getAllTimeBattingRecordStolenBases());

        //all time batting records walks
        recordsMap.put("all_time_walks",allTimeBattingRecordService.getAllTimeBattingRecordWalks());

        //all time batting records hitbypitch
        recordsMap.put("all_time_hitbypitch",allTimeBattingRecordService.getAllTimeBattingRecordHitByPitch());

        //all time batting records batting average
        recordsMap.put("all_time_batting_average",allTimeBattingRecordService.getAllTimeBattingRecordBattingAverage());

        //all time batting records slugging percentage
        recordsMap.put("all_time_slugging_percentage",allTimeBattingRecordService.getAllTimeBattingRecordSluggingPercentage());

        //all time batting records on base percentage
        recordsMap.put("all_time_on_base_percentage",allTimeBattingRecordService.getAllTimeBattingRecordOnBasePercentage());

        //all time batting records mvp
        recordsMap.put("all_time_mvp",allTimeBattingRecordService.getAllTimeBattingRecordMVP());

        return recordsMap;
    }

    @GetMapping("api/getAllTimeRecordsPitching")
    public Map<String,Object> getAllTimeRecordsPitching(){
        Map<String,Object> recordsMap = new HashMap<String, Object>();

        /////////////////////
        //ALL TIME PITCHING
        /////////////////////
        //all time pitching records games pitched
        recordsMap.put("all_time_games_pitched",allTimePitchingRecordService.getAllTimePitchingRecordGamesPitched());

        //all time pitching records wins
        recordsMap.put("all_time_wins",allTimePitchingRecordService.getAllTimePitchingRecordWins());

        //all time pitching records saves
        recordsMap.put("all_time_saves",allTimePitchingRecordService.getAllTimePitchingRecordSaves());

        //all time pitching record strikeouts
        recordsMap.put("all_time_strikeouts",allTimePitchingRecordService.getAllTimePitchingRecordStrikeouts());

        //all time pitching records era
        recordsMap.put("all_time_era",allTimePitchingRecordService.getAllTimePitchingRecordERA());

        //all time pitching records whip
        recordsMap.put("all_time_whip",allTimePitchingRecordService.getAllTimePitchingRecordWHIP());

        //all time pitching records cy young
        recordsMap.put("all_time_cy_young",allTimePitchingRecordService.getAllTimePitchingRecordCyYoung());

        return recordsMap;
    }

    @GetMapping("api/getSeasonRecordsBatting")
    public Map<String,Object> getSeasonRecordsBatting(){
        Map<String,Object> recordsMap = new HashMap<String, Object>();

        ///////////////////////
        //SINGLE SEASON BATTING
        ///////////////////////
        //single season batting records hits
        recordsMap.put("single_season_hits",singleSeasonBattingRecordService.getSingleSeasonBattingRecordHits());

        //single season batting records doubles
        recordsMap.put("single_season_doubles",singleSeasonBattingRecordService.getSingleSeasonBattingRecordDoubles());

        //single season batting records triples
        recordsMap.put("single_season_triples",singleSeasonBattingRecordService.getSingleSeasonBattingRecordTriples());

        //single season batting records homeruns
        recordsMap.put("single_season_homeruns",singleSeasonBattingRecordService.getSingleSeasonBattingRecordHomeruns());

        //single season batting records runs
        recordsMap.put("single_season_runs",singleSeasonBattingRecordService.getSingleSeasonBattingRecordRuns());

        //single season batting records rbis
        recordsMap.put("single_season_rbis",singleSeasonBattingRecordService.getSingleSeasonBattingRecordRbis());

        //single season batting records stolenbases
        recordsMap.put("single_season_stolenbases",singleSeasonBattingRecordService.getSingleSeasonBattingRecordStolenBases());

        //single season batting records walks
        recordsMap.put("single_season_walks",singleSeasonBattingRecordService.getSingleSeasonBattingRecordWalks());

        //single season batting records hitbypitch
        recordsMap.put("single_season_hitbypitch",singleSeasonBattingRecordService.getSingleSeasonBattingRecordHitByPitch());

        //single season batting records batting average
        recordsMap.put("single_season_batting_average",singleSeasonBattingRecordService.getSingleSeasonBattingRecordBattingAverage());

        //single season batting records slugging average
        recordsMap.put("single_season_slugging_percentage",singleSeasonBattingRecordService.getSingleSeasonBattingRecordSluggingPercentage());

        //single season batting records on base percentage
        recordsMap.put("single_season_on_base_percentage",singleSeasonBattingRecordService.getSingleSeasonBattingRecordOnBasePercentage());

        //single season batting records mvp
        recordsMap.put("single_season_mvp",singleSeasonBattingRecordService.getSingleSeasonBattingRecordMVP());


        return recordsMap;
    }

    @GetMapping("api/getSeasonRecordsPitching")
    public Map<String,Object> getSeasonRecordsPitching(){
        Map<String,Object> recordsMap = new HashMap<String, Object>();

        ////////////////////////
        //SINGLE SEASON PITCHING
        ////////////////////////
        //single season pitching records wins
        recordsMap.put("single_season_wins",singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordWins());

        //single season pitching records saves
        recordsMap.put("single_season_saves",singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordSaves());

        //single season batting records strikeouts
        recordsMap.put("single_season_strikeouts",singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordStrikeouts());

        //single season batting records era
        recordsMap.put("single_season_era",singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordERA());

        //single season batting records era
        recordsMap.put("single_season_whip",singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordWHIP());

        //single season batting records cy young
        recordsMap.put("single_season_cy_young",singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordCyYoung());


        return recordsMap;
    }

    @GetMapping("api/getGameRecordsBatting")
    public Map<String,Object> getGameRecordsBatting(){
        Map<String,Object> recordsMap = new HashMap<String, Object>();

        /////////////////////
        //SINGLE GAME BATTING
        /////////////////////
        //single game batting records hits
        recordsMap.put("single_game_hits",singleGameBattingRecordService.getSingleGameBattingRecordHits());

        //single game batting records doubles
        recordsMap.put("single_game_doubles",singleGameBattingRecordService.getSingleGameBattingRecordDoubles());

        //single game batting records triples
        recordsMap.put("single_game_triples",singleGameBattingRecordService.getSingleGameBattingRecordTriples());

        //single game batting records homeruns
        recordsMap.put("single_game_homeruns",singleGameBattingRecordService.getSingleGameBattingRecordHomeruns());

        //single game batting records runs
        recordsMap.put("single_game_runs",singleGameBattingRecordService.getSingleGameBattingRecordRuns());

        //single game batting records rbis
        recordsMap.put("single_game_rbis",singleGameBattingRecordService.getSingleGameBattingRecordRbis());

        //single game batting records stolenbases
        recordsMap.put("single_game_stolenbases",singleGameBattingRecordService.getSingleGameBattingRecordStolenBases());

        //single game batting records walks
        recordsMap.put("single_game_walks",singleGameBattingRecordService.getSingleGameBattingRecordWalks());

        //single game batting records hitbypitch
        recordsMap.put("single_game_hitbypitch",singleGameBattingRecordService.getSingleGameBattingRecordHitByPitch());


        return recordsMap;
    }

    @GetMapping("api/getGameRecordsPitching")
    public Map<String,Object> getGameRecordsPitching(){
        Map<String,Object> recordsMap = new HashMap<String, Object>();

        /////////////////////
        //SINGLE GAME PITCHING
        /////////////////////
        //single game pitching records strikeouts
        recordsMap.put("single_game_strikeouts",singleGamePitchingRecordService.getSingleSeasonPitchingRecordStrikeouts());


        return recordsMap;
    }
}
