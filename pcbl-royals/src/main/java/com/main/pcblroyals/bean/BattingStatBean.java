package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.text.DecimalFormat;


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

    protected String battingAverage;
    protected String onBasePercentage;
    protected String sluggingAverage;

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

    public String getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(String battingAverage) {
        this.battingAverage = battingAverage;
    }

    public String getOnBasePercentage() {
        return onBasePercentage;
    }

    public void setOnBasePercentage(String onBasePercentage) {
        this.onBasePercentage = onBasePercentage;
    }

    public String getSluggingAverage() {
        return sluggingAverage;
    }

    public void setSluggingAverage(String sluggingAverage) {
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
        float tempBattingAverage;
        if(atBats == 0){
            tempBattingAverage = 0;
        }
        else {
            tempBattingAverage = (float)singles/atBats;
            tempBattingAverage = (float)Math.round(tempBattingAverage * 1000f)/1000f;
        }

        DecimalFormat df = new DecimalFormat("0.000");
        battingAverage = df.format(tempBattingAverage);
    }

    protected void calculateOnBasePercentage(){
        float tempOnBasePercentage;
        if(atBats + walks + hitByPitch + sacrifices == 0){
            tempOnBasePercentage = 0;
            tempOnBasePercentage = (float)Math.round(tempOnBasePercentage * 1000f)/1000f;
        }
        else{
            tempOnBasePercentage = ((float)singles + walks + hitByPitch)/(atBats + walks + hitByPitch + sacrifices);
            tempOnBasePercentage = (float)Math.round(tempOnBasePercentage * 1000f)/1000f;
        }

        DecimalFormat df = new DecimalFormat("0.000");
        onBasePercentage = df.format(tempOnBasePercentage);
    }

    protected void calculateSluggingAverage(){
        float tempSluggingAverage;
        if(atBats == 0){
            tempSluggingAverage = 0;
            tempSluggingAverage = (float)Math.round(tempSluggingAverage * 1000f)/1000f;
        }
        else{
            tempSluggingAverage = ((float) singles + 2*doubles + 3*triples + 4*homeRuns)/(atBats);
            tempSluggingAverage = (float)Math.round(tempSluggingAverage * 1000f)/1000f;
        }

        DecimalFormat df = new DecimalFormat("0.000");
        sluggingAverage = df.format(tempSluggingAverage);
    }
}
