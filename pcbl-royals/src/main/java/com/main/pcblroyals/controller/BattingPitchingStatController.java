package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.BattingStatBean;
import com.main.pcblroyals.bean.PitchingStatBean;
import com.main.pcblroyals.service.BattingStatService;
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
}
