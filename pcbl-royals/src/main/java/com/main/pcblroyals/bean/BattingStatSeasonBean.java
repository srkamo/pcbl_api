package com.main.pcblroyals.bean;

import java.io.Serializable;

/**
 * Created by rblay on 10/14/18.
 */
public class BattingStatSeasonBean extends BattingStatBean implements Serializable  {
    protected int season_id;
    protected String season;
    protected int year;

    protected long numGames;

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
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

    public long getNumGames() {
        return numGames;
    }

    public void setNumGames(long numGames) {
        this.numGames = numGames;
    }


    //constructor for single season or game batting stats
    public BattingStatSeasonBean(int season_id, String season, int year, long numGames, long atBats, long singles, long doubles, long triples, long homeRuns, long walks, long hitByPitch, long sacrifices, long runs, long rbis, long stolenBases, long passedBalls, long caughtStealing, long strikeOuts) {
        this.season_id = season_id;
        this.season = season;
        this.year = year;
        this.numGames = numGames;
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

        calculateBattingAverage();
        calculateOnBasePercentage();
        calculateSluggingAverage();
    }
}
