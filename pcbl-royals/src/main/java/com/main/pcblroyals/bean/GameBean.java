package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rblay on 10/25/18.
 */
public class GameBean implements Serializable {
    protected int game_id;
    protected int opponent_id;
    protected String opponentName;
    protected boolean homeTeam;
    protected Date date;
    protected String location;
    protected String dateString;
    protected String dateTimeString;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDateTimeString() {
        return dateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
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

    public void formatDateTimeString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("PST"));
        dateTimeString = formatter.format(date);
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
    public GameBean(int game_id, int opponent_id, String opponentName, boolean homeTeam, Date date)
    {
        this.game_id = game_id;
        this.opponent_id = opponent_id;
        this.opponentName = opponentName;
        this.homeTeam = homeTeam;
        this.date = date;

        formatDateTimeString();
        formatDateString();
        formatGameString();
    }
}
