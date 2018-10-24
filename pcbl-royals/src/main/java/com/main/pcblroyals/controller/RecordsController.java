package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.SingleSeasonBattingRecordBean;
import com.main.pcblroyals.service.RecordsService;
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
public class RecordsController {

    @Autowired
    @Qualifier("recordsService")
    private RecordsService recordsService;

    @GetMapping("api/getRecords")
    public List<Object> getSingleSeasonRecordHits(){
        List<Object> recordsList = new ArrayList<Object>();

        //single season batting records hits
        List<SingleSeasonBattingRecordBean> singleSeasonBattingHits = recordsService.getSingleSeasonBattingRecordHits();
        recordsList.add(singleSeasonBattingHits);

        //single season batting records doubles
        List<SingleSeasonBattingRecordBean> singleSeasonBattingDoubles = recordsService.getSingleSeasonBattingRecordDoubles();
        recordsList.add(singleSeasonBattingDoubles);

        //singles season batting records triples
        List<SingleSeasonBattingRecordBean> singleSeasonBattingTriples = recordsService.getSingleSeasonBattingRecordDoubles();
        recordsList.add(singleSeasonBattingDoubles);

        return recordsList;
    }
}
