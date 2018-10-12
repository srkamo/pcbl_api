package com.main.pcblroyals.controller;

import com.main.pcblroyals.bean.BattingStatBean;
import com.main.pcblroyals.service.BattingStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BattingStatController {

    @Autowired
    @Qualifier("battingStatService")
    private BattingStatService battingStatService;

    @GetMapping("api/battingStatsBySeason/{id}")
    public List<BattingStatBean> getBattingStatsBySeason(@PathVariable(value = "id") int seasonId){
        return battingStatService.getBattingStatsBySeason(seasonId);
    }
}
