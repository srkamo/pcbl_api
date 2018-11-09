package com.main.pcblroyals.controller;

import com.main.pcblroyals.data.Player;
import com.main.pcblroyals.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class PlayerController {

    @Autowired
    @Qualifier("playerService")
    private PlayerService playerService;

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
        return playerService.getAllPlayer();
    }

}