package com.main.pcblroyals.bean;

import java.io.Serializable;

public class PitchingStatSeasonBean extends PitchingStatBean implements Serializable {
    protected int season_id;
    protected String season;
    protected int year;
    protected long numGames;
    protected String seasonDisplayName;

    public String getSeasonDisplayName() {
        return seasonDisplayName;
    }

    public void setSeasonDisplayName(String seasonDisplayName) {
        this.seasonDisplayName = seasonDisplayName;
    }

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

    private void formatSeasonDisplayName(){
        seasonDisplayName = season + " " + year;
    }

    public PitchingStatSeasonBean(int season_id,
                                  String season,
                                  int year,
                                  long numGames,
                                  long wins,
                                  long losses,
                                  long ties,
                                  long saves,
                                  double inningsPitchedRaw,
                                  long earnedRuns,
                                  long totalRuns,
                                  long strikeouts,
                                  long walks,
                                  long hitByPitch,
                                  long hits,
                                  long wildPitches,
                                  long stolenBases,
                                  long pickoffs)
    {
        this.season_id = season_id;
        this.season = season;
        this.year = year;
        this.numGames = numGames;
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

        formatSeasonDisplayName();
        formatInningsPitched();
        calculateEarnedRunAverage();
        calculateWalksAndHitsPerInning();
    }
}
