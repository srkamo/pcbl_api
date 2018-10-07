package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.CareerBattingStat;
import com.main.pcblroyals.data.Player;
import com.main.pcblroyals.service.CareerBattingStatService;
import com.main.pcblroyals.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class CareerBattingStatController {

    @Autowired
    @Qualifier("careerBattingStatService")
    private CareerBattingStatService careerBattingStatService;

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

    @GetMapping("api/viewCareerStatsAllPlayer")
    public  List<CareerBattingStat> viewCareerStatsAllPlayer(){
        return careerBattingStatService.getAllCareerStats();
    }

}