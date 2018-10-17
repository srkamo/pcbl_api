package com.main.pcblroyals.bean;

import java.io.Serializable;

public class AllTimeSeasonBean implements Serializable {

    private long wins;
    private long ties;
    private long losses;

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getTies() {
        return ties;
    }

    public void setTies(long ties) {
        this.ties = ties;
    }

    public long getLosses() {
        return losses;
    }

    public void setLosses(long losses) {
        this.losses = losses;
    }

    // default constructor

    public AllTimeSeasonBean(long wins, long ties, long losses) {
        this.wins = wins;
        this.ties = ties;
        this.losses = losses;
    }
}
