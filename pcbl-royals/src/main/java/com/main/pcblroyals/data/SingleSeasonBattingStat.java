package com.main.pcblroyals.data;

public class SingleSeasonBattingStat {

    private int id;
    private int atBats;
    private int singles;
    private int doubles;
    private int triples;
    private int homeRuns;
    private int walks;
    private int hitByPitch;
    private int sacrifices;
    private int runs;
    private int rbis;
    private int stolenBases;
    private int passedBalls;
    private int caughtStealing;
    private int strikeOuts;
    private int seasonId;
    private int playerId;

    public SingleSeasonBattingStat(int atBats, int singles, int doubles, int triples, int homeRuns, int walks, int hitByPitch, int sacrifices, int runs, int rbis, int stolenBases, int passedBalls, int caughtStealing, int strikeOuts, int seasonId, int playerId) {
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
        this.seasonId = seasonId;
        this.playerId = playerId;
    }
}
