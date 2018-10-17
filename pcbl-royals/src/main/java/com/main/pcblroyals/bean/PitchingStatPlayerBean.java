package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.text.DecimalFormat;

public class PitchingStatPlayerBean extends PitchingStatBean implements Serializable {

    protected int player_id;
    protected String firstName;
    protected String lastName;
    protected long numGames;

    public int getPlayer_id() {

        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getNumGames() {
        return numGames;
    }

    public void setNumGames(long numGames) {
        this.numGames = numGames;
    }


    public PitchingStatPlayerBean(int player_id,
                            String firstName,
                            String lastName,
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
        this.player_id = player_id;
        this.firstName = firstName;
        this.lastName = lastName;
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

        formatInningsPitched();
        calculateEarnedRunAverage();
        calculateWalksAndHitsPerInning();
    }

    public PitchingStatPlayerBean(int player_id,
                            String firstName,
                            String lastName,
                            int numGames,
                            int wins,
                            int losses,
                            int ties,
                            int saves,
                            double inningsPitchedRaw,
                            int earnedRuns,
                            int totalRuns,
                            int strikeouts,
                            int walks,
                            int hitByPitch,
                            int hits,
                            int wildPitches,
                            int stolenBases,
                            int pickoffs)
    {
        this.player_id = player_id;
        this.firstName = firstName;
        this.lastName = lastName;
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

        formatInningsPitchedRaw();
        calculateEarnedRunAverage();
        calculateWalksAndHitsPerInning();
    }
}
