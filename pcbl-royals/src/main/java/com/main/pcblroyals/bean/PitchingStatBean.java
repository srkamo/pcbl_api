package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.text.DecimalFormat;


public class PitchingStatBean implements Serializable {

    protected long wins;
    protected long losses;
    protected long ties;
    protected long saves;
    protected double inningsPitched;
    protected double inningsPitchedRaw;
    protected long earnedRuns;
    protected long totalRuns;
    protected long strikeouts;
    protected long walks;
    protected long hitByPitch;
    protected long hits;
    protected long wildPitches;
    protected long stolenBases;
    protected long pickoffs;

    protected String earnedRunAverage;
    protected String walksAndHitsPerInning;

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

    public long getSaves() {
        return saves;
    }

    public void setSaves(long saves) {
        this.saves = saves;
    }

    public double getInningsPitched() {
        return inningsPitched;
    }

    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched = inningsPitched;
    }

    public double getInningsPitchedRaw() {
        return inningsPitchedRaw;
    }

    public void setInningsPitchedRaw(double inningsPitchedRaw) {
        this.inningsPitchedRaw = inningsPitchedRaw;
    }

    public long getEarnedRuns() {
        return earnedRuns;
    }

    public void setEarnedRuns(long earnedRuns) {
        this.earnedRuns = earnedRuns;
    }

    public long getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(long totalRuns) {
        this.totalRuns = totalRuns;
    }

    public long getStrikeouts() {
        return strikeouts;
    }

    public void setStrikeouts(long strikeouts) {
        this.strikeouts = strikeouts;
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

    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }

    public long getWildPitches() {
        return wildPitches;
    }

    public void setWildPitches(long wildPitches) {
        this.wildPitches = wildPitches;
    }

    public long getStolenBases() {
        return stolenBases;
    }

    public void setStolenBases(long stolenBases) {
        this.stolenBases = stolenBases;
    }

    public long getPickoffs() {
        return pickoffs;
    }

    public void setPickoffs(long pickoffs) {
        this.pickoffs = pickoffs;
    }

    public String getEarnedRunAverage() {
        return earnedRunAverage;
    }

    public void setEarnedRunAverage(String earnedRunAverage) {
        this.earnedRunAverage = earnedRunAverage;
    }

    public String getWalksAndHitsPerInning() {
        return walksAndHitsPerInning;
    }

    public void setWalksAndHitsPerInning(String walksAndHitsPerInning) {
        this.walksAndHitsPerInning = walksAndHitsPerInning;
    }

    public void formatInningsPitched(){
        long integerPart = (long) inningsPitchedRaw;
        double decimalPart = inningsPitchedRaw - integerPart;
        double roundedDecimalPart = (double)Math.round(decimalPart * 10f)/10f;
        inningsPitched = (double) integerPart;

        if(roundedDecimalPart > 0.1 && roundedDecimalPart < 0.45){
            inningsPitched += 0.1;
        }
        if(roundedDecimalPart > 0.45 && roundedDecimalPart < 0.75){
            inningsPitched += 0.2;
        }
        if(roundedDecimalPart > 0.75 && roundedDecimalPart < 1.0){
            inningsPitched += 1.0;
        }
    }

    protected void formatInningsPitchedRaw(){
        long integerPart = (long) inningsPitched;
        double decimalPart = inningsPitched - integerPart;
        inningsPitchedRaw = ((double) 1/3)*decimalPart + integerPart;
    }

    protected void calculateEarnedRunAverage(){
        double tempEarnedRunAverage = 0f;
        if(inningsPitchedRaw == 0){
            tempEarnedRunAverage = 0f;
        }
        else {
            //REDO CALCULATION
            tempEarnedRunAverage = ((double) earnedRuns*9)/inningsPitchedRaw;
            tempEarnedRunAverage = (double) Math.round(tempEarnedRunAverage * 1000f)/1000f;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        earnedRunAverage = df.format(tempEarnedRunAverage);
    }

    protected void calculateWalksAndHitsPerInning(){
        double tempWalksAndHitsPerInning = 0f;
        if(inningsPitchedRaw == 0){
            tempWalksAndHitsPerInning = 0f;
        }
        else {
            tempWalksAndHitsPerInning = (((double) hits + walks)/inningsPitchedRaw);
            tempWalksAndHitsPerInning = (double) Math.round(tempWalksAndHitsPerInning * 1000f)/1000f;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        walksAndHitsPerInning = df.format(tempWalksAndHitsPerInning);
    }

    public PitchingStatBean()
    {
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.saves = 0;
        this.inningsPitchedRaw = 0;
        this.earnedRuns = 0;
        this.totalRuns = 0;
        this.strikeouts = 0;
        this.walks = 0;
        this.hitByPitch = 0;
        this.hits = 0;
        this.wildPitches = 0;
        this.stolenBases = 0;
        this.pickoffs = 0;

        formatInningsPitched();
        calculateEarnedRunAverage();
        calculateWalksAndHitsPerInning();
    }

    public PitchingStatBean(long wins, long losses, long ties, long saves, double inningsPitchedRaw, long earnedRuns, long totalRuns, long strikeouts, long walks, long hitByPitch, long hits, long wildPitches, long stolenBases, long pickoffs) {
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
}
