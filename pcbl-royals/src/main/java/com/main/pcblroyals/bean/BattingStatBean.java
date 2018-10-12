package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * Created by rblay on 10/10/18.
 */
public class BattingStatBean implements Serializable {

    private int player_id;
    private String firstName;
    private String lastName;

    private long numGames;

    private long atBats;
    private long singles;
    private long doubles;
    private long triples;
    private long homeRuns;
    private long walks;
    private long hitByPitch;
    private long sacrifices;
    private long runs;
    private long rbis;
    private long stolenBases;
    private long passedBalls;
    private long caughtStealing;
    private long strikeOuts;

    private float battingAverage;
    private float onBasePercentage;
    private float sluggingAverage;


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

    public long getAtBats() {
        return atBats;
    }

    public void setAtBats(long atBats) {
        this.atBats = atBats;
    }

    public long getSingles() {
        return singles;
    }

    public void setSingles(long singles) {
        this.singles = singles;
    }

    public long getDoubles() {
        return doubles;
    }

    public void setDoubles(long doubles) {
        this.doubles = doubles;
    }

    public long getTriples() {
        return triples;
    }

    public void setTriples(long triples) {
        this.triples = triples;
    }

    public long getHomeRuns() {
        return homeRuns;
    }

    public void setHomeRuns(long homeRuns) {
        this.homeRuns = homeRuns;
    }

    public long getWalks() {
        return walks;
    }

    public void setWalks(long walks) {
        this.walks = walks;
    }

    public long getHitByPitch() {
        return hitByPitch;
    }

    public void setHitByPitch(long hitByPitch) {
        this.hitByPitch = hitByPitch;
    }

    public long getSacrifices() {
        return sacrifices;
    }

    public void setSacrifices(long sacrifices) {
        this.sacrifices = sacrifices;
    }

    public long getRuns() {
        return runs;
    }

    public void setRuns(long runs) {
        this.runs = runs;
    }

    public long getRbis() {
        return rbis;
    }

    public void setRbis(long rbis) {
        this.rbis = rbis;
    }

    public long getStolenBases() {
        return stolenBases;
    }

    public void setStolenBases(long stolenBases) {
        this.stolenBases = stolenBases;
    }

    public long getPassedBalls() {
        return passedBalls;
    }

    public void setPassedBalls(long passedBalls) {
        this.passedBalls = passedBalls;
    }

    public long getCaughtStealing() {
        return caughtStealing;
    }

    public void setCaughtStealing(long caughtStealing) {
        this.caughtStealing = caughtStealing;
    }

    public long getStrikeOuts() {
        return strikeOuts;
    }

    public void setStrikeOuts(long strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    public float getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(float battingAverage) {
        this.battingAverage = battingAverage;
    }

    public float getOnBasePercentage() {
        return onBasePercentage;
    }

    public void setOnBasePercentage(float onBasePercentage) {
        this.onBasePercentage = onBasePercentage;
    }

    public float getSluggingAverage() {
        return sluggingAverage;
    }

    public void setSluggingAverage(float sluggingAverage) {
        this.sluggingAverage = sluggingAverage;
    }

    public BattingStatBean(int player_id, String firstName, String lastName, long numGames, long atBats, long singles, long doubles, long triples, long homeRuns, long walks, long hitByPitch, long sacrifices, long runs, long rbis, long stolenBases, long passedBalls, long caughtStealing, long strikeOuts) {
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

        calculateBattingAverage();
        calculateOnBasePercentage();
        calculateSluggingAverage();
    }

    private void calculateBattingAverage(){
        if(atBats == 0){
            battingAverage = 0;
            battingAverage = (float)Math.round(battingAverage * 1000f)/1000f;
        }
        else {
            battingAverage = (float)singles/atBats;
            battingAverage = (float)Math.round(battingAverage * 1000f)/1000f;
        }
    }

    private void calculateOnBasePercentage(){
        if(atBats + walks + hitByPitch + sacrifices == 0){
            onBasePercentage = 0;
            onBasePercentage = (float)Math.round(onBasePercentage * 1000f)/1000f;
        }
        else{
            onBasePercentage = ((float)singles + walks + hitByPitch)/(atBats + walks + hitByPitch + sacrifices);
            onBasePercentage = (float)Math.round(onBasePercentage * 1000f)/1000f;
        }
    }

    private void calculateSluggingAverage(){
        if(atBats == 0){
            sluggingAverage = 0;
            sluggingAverage = (float)Math.round(sluggingAverage * 1000f)/1000f;
        }
        else {
            sluggingAverage = ((float) singles + 2*doubles + 3*triples + 4*homeRuns)/(atBats);
            sluggingAverage = (float)Math.round(sluggingAverage * 1000f)/1000f;
        }
    }
}
