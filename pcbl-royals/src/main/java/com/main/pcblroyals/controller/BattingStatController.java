package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.BattingStat;
import com.main.pcblroyals.data.SeasonBattingStat;
import com.main.pcblroyals.service.BattingStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class BattingStatController {

    @Autowired
    @Qualifier("battingStatService")
    private BattingStatService battingStatService;

    @GetMapping("api/viewBattingStatsBySeason/{id}")
    public List<SeasonBattingStat> viewBattingStatsBySeason(@PathVariable(value = "id") int seasonId){
        return battingStatService.getSeasonStats(seasonId);
    }
}
