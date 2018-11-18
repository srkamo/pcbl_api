package com.main.pcblroyals.bean;


import com.main.pcblroyals.data.BattingStat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BattingStatGameBean extends BattingStatBean implements Serializable {
    protected int game_id;
    protected int opponent_id;
    protected String opponentName;
    protected boolean homeTeam;
    protected Date date;
    protected String dateString;
    protected String gameString;

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getOpponent_id() {
        return opponent_id;
    }

    public void setOpponent_id(int opponent_id) {
        this.opponent_id = opponent_id;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public boolean isHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(boolean homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getGameString() {
        return gameString;
    }

    public void setGameString(String gameString) {
        this.gameString = gameString;
    }

    public void formatDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        dateString = formatter.format(date);
    }

    public void formatGameString() {
        if(homeTeam){
            gameString = dateString + " v.s. " + opponentName;
        }
        else {
            gameString = dateString + " @ " + opponentName;
        }
    }

    //constructor for single game batting stats
    public BattingStatGameBean(int game_id, int opponent_id, String opponentName, boolean homeTeam, Date date, long atBats, long singles, long doubles, long triples, long homeRuns, long walks, long hitByPitch, long sacrifices, long runs, long rbis, long stolenBases, long passedBalls, long caughtStealing, long strikeOuts) {
        this.game_id = game_id;
        this.opponent_id = opponent_id;
        this.opponentName = opponentName;
        this.homeTeam = homeTeam;
        this.date = date;
        this.atBats = atBats;
        this.singles = singles;
        this.doubles = doubles;
        this.triples = triples;
        this.homeRuns = homeRuns;
        this.walks = walks;
        this.hitByPitch = hitByPitch;
        this.sacrifices = sacrifices;
        this.runs = runs;
        this.rbis = rbis;
        this.stolenBases = stolenBases;
        this.passedBalls = passedBalls;
        this.caughtStealing = caughtStealing;
        this.strikeOuts = strikeOuts;

        calculateHits();
        calculateBattingAverage();
        calculateOnBasePercentage();
        calculateSluggingAverage();
        formatDateString();
        formatGameString();
    }

    //constructor for single game batting stats
    public BattingStatGameBean(){
        this.game_id = 0;
        this.opponent_id = 0;
        this.opponentName = "";
        this.homeTeam = false;
        this.date = new java.util.Date();
        this.atBats = 0;
        this.singles = 0;
        this.doubles = 0;
        this.triples = 0;
        this.homeRuns = 0;
        this.walks = 0;
        this.hitByPitch = 0;
        this.sacrifices = 0;
        this.runs = 0;
        this.rbis = 0;
        this.stolenBases = 0;
        this.passedBalls = 0;
        this.caughtStealing = 0;
        this.strikeOuts = 0;
        this.dateString = "";
        this.gameString = "Player has not batted";

        calculateHits();
        calculateBattingAverage();
        calculateOnBasePercentage();
        calculateSluggingAverage();
    }


}
