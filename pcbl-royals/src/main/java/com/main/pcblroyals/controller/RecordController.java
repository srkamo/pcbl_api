package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.SingleSeasonBattingRecordBean;
import com.main.pcblroyals.service.RecordService;
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
    @Qualifier("recordService")
    private RecordService recordService;

    @GetMapping("api/getRecords")
    public List<Object> getSingleSeasonRecordHits(){
        List<Object> recordsList = new ArrayList<Object>();

        //single season batting records hits
        recordsList.add(recordService.getSingleSeasonBattingRecordHits());

        //single season batting records doubles
        recordsList.add(recordService.getSingleSeasonBattingRecordDoubles());

        //single season batting records triples
        recordsList.add(recordService.getSingleSeasonBattingRecordTriples());

        //single season batting records homeruns
        recordsList.add(recordService.getSingleSeasonBattingRecordHomeruns());

        //single season batting records runs
        recordsList.add(recordService.getSingleSeasonBattingRecordRuns());

        //single season batting records rbis
        recordsList.add(recordService.getSingleSeasonBattingRecordRbis());

        //single season batting records stolenbases
        recordsList.add(recordService.getSingleSeasonBattingRecordStolenBases());

        //single season batting records walks
        recordsList.add(recordService.getSingleSeasonBattingRecordWalks());

        //single season batting records hitbypitch
        recordsList.add(recordService.getSingleSeasonBattingRecordHitByPitch());

        //single season batting records batting average
        recordsList.add(recordService.getSingleSeasonBattingRecordBattingAverage());

        //single season batting records slugging average
        recordsList.add(recordService.getSingleSeasonBattingRecordSluggingPercentage());

        //single season batting records on base percentage
        recordsList.add(recordService.getSingleSeasonBattingRecordOnBasePercentage());

        //single season batting records on base percentage
        recordsList.add(recordService.getSingleSeasonBattingRecordMVP());

        return recordsList;
    }
}
