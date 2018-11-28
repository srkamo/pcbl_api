package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PitchingStatGameBean extends PitchingStatBean implements Serializable {
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

    //constructor for single game pitching stats
    public PitchingStatGameBean(int game_id, int opponent_id, String opponentName, boolean homeTeam, Date date, long wins, long losses, long ties, long saves, double inningsPitchedRaw, long earnedRuns, long totalRuns, long strikeouts, long walks, long hitByPitch, long hits, long wildPitches, long stolenBases, long pickoffs) {
        this.game_id = game_id;
        this.opponent_id = opponent_id;
        this.opponentName = opponentName;
        this.homeTeam = homeTeam;
        this.date = date;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.saves = saves;
        this.inningsPitchedRaw = inningsPitchedRaw;
        this.earnedRuns = earnedRuns;
        this.totalRuns = totalRuns;
        this.strikeouts = strikeouts;
        this.walks = walks;
        this.hitByPitch = hitByPitch;
        this.hits = hits;
        this.wildPitches = wildPitches;
        this.stolenBases = stolenBases;
        this.pickoffs = pickoffs;

        formatInningsPitched();
        calculateEarnedRunAverage();
        calculateWalksAndHitsPerInning();
        formatDateString();
        formatGameString();
    }

    public PitchingStatGameBean(){
        this.game_id = 0;
        this.opponent_id = 0;
        this.opponentName = "";
        this.homeTeam = false;
        this.date = new java.util.Date();
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.saves = 0;
        this.inningsPitchedRaw = 0;
        this.earnedRuns = 0;
        this.totalRuns = 0;
        this.strikeouts = 0;
        this.walks = 0;
        this.hitByPitch = 0;
        this.hits = 0;
        this.wildPitches = 0;
        this.stolenBases = 0;
        this.pickoffs = 0;
        this.dateString = "";
        this.gameString = "Player has not pitched";

        formatInningsPitched();
        calculateEarnedRunAverage();
        calculateWalksAndHitsPerInning();
    }
}
