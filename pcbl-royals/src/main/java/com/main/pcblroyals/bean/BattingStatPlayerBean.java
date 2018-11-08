package com.main.pcblroyals.bean;

import java.io.Serializable;

/**
 * Created by rblay on 10/14/18.
 */
public class BattingStatPlayerBean extends BattingStatBean implements Serializable {

    protected int player_id;
    protected String firstName;
    protected String lastName;
    protected String displayName;
    protected long numGames;

    public long getPlayer_id() {
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

    public String getDisplayName() { return displayName; }

    public void setDisplayName(String displayName) { this.displayName = displayName;}

    protected void formatDisplayName(){
        displayName = lastName + ", " + firstName;
    }

    //constructor for single season or game batting stats
    public BattingStatPlayerBean(int player_id, String firstName, String lastName, long numGames, long atBats, long singles, long doubles, long triples, long homeRuns, long walks, long hitByPitch, long sacrifices, long runs, long rbis, long stolenBases, long passedBalls, long caughtStealing, long strikeOuts) {
        this.player_id = player_id;
        this.firstName = firstName;
        this.lastName = lastName;
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

        calculateHits();
        formatDisplayName();
        calculateBattingAverage();
        calculateOnBasePercentage();
        calculateSluggingAverage();
    }

    //constructor for career batting stats
    public BattingStatPlayerBean(int player_id, String firstName, String lastName, int numGames, int atBats, int singles, int doubles, int triples, int homeRuns, int walks, int hitByPitch, int sacrifices, int runs, int rbis, int stolenBases, int passedBalls, int caughtStealing, int strikeOuts) {
        this.player_id = player_id;
        this.firstName = firstName;
        this.lastName = lastName;
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

        calculateHits();
        formatDisplayName();
        calculateBattingAverage();
        calculateOnBasePercentage();
        calculateSluggingAverage();
    }
}
