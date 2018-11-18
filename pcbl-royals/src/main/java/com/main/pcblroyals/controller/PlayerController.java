package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.Player;
import com.main.pcblroyals.data.Season;
import com.main.pcblroyals.service.PlayerService;
import com.main.pcblroyals.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
public class PlayerController {

    @Autowired
    @Qualifier("playerService")
    private PlayerService playerService;

    @Autowired
    @Qualifier("seasonService")
    private SeasonService seasonService;

    @PutMapping("/api/changePlayer")
    public void changeExistingPlayer(@Valid @RequestBody Player player) {
        playerService.modifyPlayer(player);
    }

    @DeleteMapping("/api/removePlayer/{id}")
    public void removePlayer(@PathVariable(value = "id") int playerId) {
        playerService.removePlayer(playerId);
    }

    @GetMapping("/api/viewSinglePlayer/{id}")
    public Player viewPlayerById(@PathVariable(value = "id") int playerId) {
        return playerService.selectPlayerById(playerId);
    }

    @GetMapping("/api/viewAllPlayers")
    public List<Player> viewAllPlayers() {
        List<Player> playerList = playerService.getAllPlayer();
        Collections.sort(playerList);
        return playerList;
    }

    @GetMapping("/api/getAllPlayersForSeason/{seasonId}")
    public List<Player> getAllPlayersForSeason(@PathVariable(value = "seasonId") int seasonId){
        List<Player> playerList = seasonService.getSeasonById(seasonId).getPlayers();
        Collections.sort(playerList);
        return playerList;
    }

}