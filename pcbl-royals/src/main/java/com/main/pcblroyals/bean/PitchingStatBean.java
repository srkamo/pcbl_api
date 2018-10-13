package com.main.pcblroyals.bean;

import java.io.Serializable;

/**
 * Created by rblay on 10/11/18.
 */
public class PitchingStatBean implements Serializable {

    private int player_id;
    private String firstName;
    private String lastName;
    private long numGames;
    private long wins;
    private long losses;
    private long ties;
    private long saves;
    private double inningsPitched;
    private double inningsPitchedRaw;
    private long earnedRuns;
    private long totalRuns;
    private long strikeouts;
    private long walks;
    private long hitByPitch;
    private long hits;
    private long wildPitches;
    private long stolenBases;
    private long pickoffs;

    private double earnedRunAverage;
    private double walksAndHitsPerInning;

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

    public double getEarnedRunAverage() {
        return earnedRunAverage;
    }

    public void setEarnedRunAverage(double earnedRunAverage) {
        this.earnedRunAverage = earnedRunAverage;
    }

    public double getWalksAndHitsPerInning() {
        return walksAndHitsPerInning;
    }

    public void setWalksAndHitsPerInning(double walksAndHitsPerInning) {
        this.walksAndHitsPerInning = walksAndHitsPerInning;
    }

    public void formatInningsPitched(){
        long integerPart = (long) inningsPitchedRaw;
        double decimalPart = inningsPitchedRaw - integerPart;
        double roundedDecimalPart = (float)Math.round(decimalPart * 10f)/10f;
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

    public void formatInningsPitchedRaw(){
        long integerPart = (long) inningsPitched;
        double decimalPart = inningsPitched - integerPart;
        inningsPitchedRaw = ((double) 1/3)*decimalPart + integerPart;
        System.out.println("--------");
        System.out.println(inningsPitched);
        System.out.println(decimalPart);
        System.out.println(decimalPart*(1/3));
        System.out.println(inningsPitchedRaw);
    }

    public void calculateEarnedRunAverage(){
        earnedRunAverage = 0f;
        if(inningsPitchedRaw == 0){
            earnedRunAverage = 0f;
            earnedRunAverage = (double) Math.round(earnedRunAverage * 1000f)/1000f;
        }
        else {
            //REDO CALCULATION
            earnedRunAverage = ((double) earnedRuns*9)/inningsPitchedRaw;
            earnedRunAverage = (double) Math.round(earnedRunAverage * 1000f)/1000f;
        }
    }

    public void calculateWalksAndHitsPerInning(){
        walksAndHitsPerInning = 0f;
        if(inningsPitchedRaw == 0){
            walksAndHitsPerInning = 0f;
            walksAndHitsPerInning = (double) Math.round(walksAndHitsPerInning * 1000f)/1000f;
        }
        else {
            walksAndHitsPerInning = (((double) hits + walks)/inningsPitchedRaw);
            walksAndHitsPerInning = (double) Math.round(walksAndHitsPerInning * 1000f)/1000f;
        }
    }

    public PitchingStatBean(int player_id,
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

    public PitchingStatBean(int player_id,
                            String firstName,
                            String lastName,
                            int numGames,
                            int wins,
                            int losses,
                            int ties,
                            int saves,
                            float inningsPitchedRaw,
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

        //formatInningsPitchedRaw();
        formatInningsPitched();
        calculateEarnedRunAverage();
        calculateWalksAndHitsPerInning();
    }


}
