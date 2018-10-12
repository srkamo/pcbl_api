package com.main.pcblroyals.service;

import com.main.pcblroyals.dao.GameDao;
import com.main.pcblroyals.data.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("gameService")
public class GameService {

    @Autowired
    @Qualifier("gameDao")
    private GameDao gameDao;

    @Transactional
    public List<Game> getMostRecentGames() {
        List<Game> allGames = gameDao.getAllGames();
        List<Game> mostRecentGames= new ArrayList<Game>();
        mostRecentGames.add(allGames.get(0));
        mostRecentGames.add(allGames.get(1));
        mostRecentGames.add(allGames.get(2));
        return mostRecentGames;
    }
}
