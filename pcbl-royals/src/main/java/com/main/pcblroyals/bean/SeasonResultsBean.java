package com.main.pcblroyals.bean;

import java.io.Serializable;

/**
 * Created by rblay on 11/13/18.
 */
public class SeasonResultsBean implements Serializable {
    protected int id;
    protected String season;
    protected int year;
    protected long wins;
    protected long losses;
    protected long ties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getLosses() {
        return losses;
    }

    public void setLosses(long losses) {
        this.losses = losses;
    }

    public long getTies() {
        return ties;
    }

    public void setTies(long ties) {
        this.ties = ties;
    }

    public SeasonResultsBean(int id, String season, int year, long wins, long losses, long ties) {
        this.id = id;
        this.season = season;
        this.year = year;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public SeasonResultsBean(){
        this.id = 0;
        this.season = "";
        this.year = 0;
        this.wins = 0;
        this.losses = 0;
    }
}
