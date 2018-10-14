package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.io.StreamCorruptedException;


public class BattingStatBean implements Serializable {

    protected long atBats;
    protected long singles;
    protected long doubles;
    protected long triples;
    protected long homeRuns;
    protected long walks;
    protected long hitByPitch;
    protected long sacrifices;
    protected long runs;
    protected long rbis;
    protected long stolenBases;
    protected long passedBalls;
    protected long caughtStealing;
    protected long strikeOuts;

    protected float battingAverage;
    protected float onBasePercentage;
    protected float sluggingAverage;

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

    //constructor for single season or game batting stats
    public BattingStatBean(long atBats, long singles, long doubles, long triples, long homeRuns, long walks, long hitByPitch, long sacrifices, long runs, long rbis, long stolenBases, long passedBalls, long caughtStealing, long strikeOuts) {
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

    //default constructor
    public BattingStatBean() {
        this.atBats = 0;
        this.singles = 0;
        this.doubles = 0;
        this.triples = 0;
        this.homeRuns = 0;
        this.walks = 0;
        this.hitByPitch = 0;
        this.sacrifices = 0;
        this.runs = 0;
        this.rbis = 0;
        this.stolenBases = 0;
        this.passedBalls = 0;
        this.caughtStealing = 0;
        this.strikeOuts = 0;

        calculateBattingAverage();
        calculateOnBasePercentage();
        calculateSluggingAverage();
    }

    protected void calculateBattingAverage(){
        if(atBats == 0){
            battingAverage = 0;
            battingAverage = (float)Math.round(battingAverage * 1000f)/1000f;
        }
        else {
            battingAverage = (float)singles/atBats;
            battingAverage = (float)Math.round(battingAverage * 1000f)/1000f;
        }
    }

    protected void calculateOnBasePercentage(){
        if(atBats + walks + hitByPitch + sacrifices == 0){
            onBasePercentage = 0;
            onBasePercentage = (float)Math.round(onBasePercentage * 1000f)/1000f;
        }
        else{
            onBasePercentage = ((float)singles + walks + hitByPitch)/(atBats + walks + hitByPitch + sacrifices);
            onBasePercentage = (float)Math.round(onBasePercentage * 1000f)/1000f;
        }
    }

    protected void calculateSluggingAverage(){
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
