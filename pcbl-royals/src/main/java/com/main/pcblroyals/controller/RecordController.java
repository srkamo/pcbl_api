package com.main.pcblroyals.controller;

import com.main.pcblroyals.service.SingleGameBattingRecordService;
import com.main.pcblroyals.service.SingleSeasonBattingRecordService;
import com.main.pcblroyals.service.SingleSeasonPitchingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("api/getRecords")
    public List<Object> getSingleSeasonRecordHits(){
        List<Object> recordsList = new ArrayList<Object>();
        ///////////////////////
        //SINGLE SEASON BATTING
        ///////////////////////
        //single season batting records hits
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordHits());

        //single season batting records doubles
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordDoubles());

        //single season batting records triples
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordTriples());

        //single season batting records homeruns
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordHomeruns());

        //single season batting records runs
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordRuns());

        //single season batting records rbis
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordRbis());

        //single season batting records stolenbases
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordStolenBases());

        //single season batting records walks
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordWalks());

        //single season batting records hitbypitch
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordHitByPitch());

        //single season batting records batting average
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordBattingAverage());

        //single season batting records slugging average
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordSluggingPercentage());

        //single season batting records on base percentage
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordOnBasePercentage());

        //single season batting records mvp
        recordsList.add(singleSeasonBattingRecordService.getSingleSeasonBattingRecordMVP());

        ////////////////////////
        //SINGLE SEASON PITCHING
        ////////////////////////
        //single season pitching records wins
        recordsList.add(singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordWins());

        //single season pitching records wins
        recordsList.add(singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordSaves());

        //single season batting records strikeouts
        recordsList.add(singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordStrikeouts());

        //single season batting records era
        recordsList.add(singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordERA());

        //single season batting records era
        recordsList.add(singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordWHIP());

        //single season batting records cy young
        recordsList.add(singleSeasonPitchingRecordService.getSingleSeasonPitchingRecordCyYoung());

        /////////////////////
        //SINGLE GAME BATTING
        /////////////////////
        //single game batting records hits
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordHits());

        //single game batting records doubles
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordDoubles());

        //single game batting records triples
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordTriples());

        //single game batting records homeruns
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordHomeruns());

        //single game batting records runs
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordRuns());

        //single game batting records rbis
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordRbis());

        //single game batting records stolenbases
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordStolenBases());

        //single game batting records walks
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordWalks());

        //single game batting records hitbypitch
        recordsList.add(singleGameBattingRecordService.getSingleGameBattingRecordHitByPitch());

        return recordsList;
    }
}
