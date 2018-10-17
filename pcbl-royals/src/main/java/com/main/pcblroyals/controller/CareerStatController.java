package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.CareerBattingStat;
import com.main.pcblroyals.data.Player;
import com.main.pcblroyals.service.CareerBattingStatService;
import com.main.pcblroyals.service.CareerPitchingStatService;
import com.main.pcblroyals.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CareerStatController {

    @Autowired
    @Qualifier("careerBattingStatService")
    private CareerBattingStatService careerBattingStatService;

    @Autowired
    @Qualifier("careerPitchingStatService")
    private CareerPitchingStatService careerPitchingStatService;

//    @PostMapping("/api/createPlayer")
//    public void createNewPlayer(@Valid @RequestBody Player player) {
//        playerService.addPlayer(player);
//    }
//
//    @PutMapping("/api/changePlayer")
//    public void changeExistingPlayer(@Valid @RequestBody Player player) {
//        playerService.modifyPlayer(player);
//    }
//
//    @DeleteMapping("/api/removePlayer/{id}")
//    public void removePlayer(@PathVariable(value = "id") int playerId) {
//        playerService.removePlayer(playerId);
//    }
//
//    @GetMapping("/api/viewSinglePlayer/{id}")
//    public Player viewPlayerById(@PathVariable(value = "id") int playerId) {
//        return playerService.getPlayerById(playerId);
//    }
//
//    @GetMapping("/api/viewAllPlayer")
//    public List<Player> viewAllPlayer() {
//        return playerService.getAllPlayer();
//    }

    // career stats page
    @GetMapping("api/viewCareerStatsAllPlayer")
    public  List<Object>  viewCareerStatsAllPlayer(){

        List<Object> battingPitchingStats = new ArrayList<>();

        // career batting stats all players
        battingPitchingStats.add(careerBattingStatService.getAllCareerStats());
        // all time batting stat for team
        battingPitchingStats.add(careerBattingStatService.getAllTimeBattingStat());

        // career pitching stats all players
        battingPitchingStats.add(careerPitchingStatService.getAllCareerPitchingStats());
        // all time pitching stat for team

        return battingPitchingStats;
    }

}