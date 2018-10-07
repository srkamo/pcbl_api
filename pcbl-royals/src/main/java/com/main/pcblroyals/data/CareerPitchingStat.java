package com.main.pcblroyals.data;

import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "pitching_career_stats")
@Table (name="pitching_career_stats", schema="PCBL")
public class CareerPitchingStat {
	private int id;
    private Player player;
    private int games;
    private float innings;
    private int earnedRuns;
    private int totalRuns;
    private int strikeouts;
    private int walks;
    private int hitByPitch;
    private int hits;
    private int wildPitches;
    private int stolenBases;
    private int pickoffs;
    private int wins;
    private int losses;
    private int ties;
    private int saves;

	private float earnedRunAverage;
	private float walksAndHitsPerInning;


    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name="player_id", nullable=false)
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Column (name="games", nullable=false)
	public int getGames() {
		return games;
	}
	public void setGames(int games) {
		this.games = games;
	}
	
	@Column (name="innings", nullable=false)
	public float getInnings() {
		return innings;
	}
	public void setInnings(float innings) {
		this.innings = innings;
	}

	@Column (name="earned_runs", nullable=false)
	public int getEarnedRuns() {
		return earnedRuns;
	}
	public void setEarnedRuns(int earnedRuns) {
		this.earnedRuns = earnedRuns;
	}

	@Column (name="total_runs", nullable=false)
	public int getTotalRuns() {
		return totalRuns;
	}
	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	@Column (name="strikeouts", nullable=false)
	public int getStrikeouts() {
		return strikeouts;
	}
	public void setStrikeouts(int strikeouts) {
		this.strikeouts = strikeouts;
	}

	@Column (name="walks", nullable=false)
	public int getWalks() {
		return walks;
	}
	public void setWalks(int walks) {
		this.walks = walks;
	}

	@Column (name="hitbypitch", nullable=false)
	public int getHitByPitch() {
		return hitByPitch;
	}
	public void setHitByPitch(int hitByPitch) {
		this.hitByPitch = hitByPitch;
	}

	@Column (name="hits", nullable=false)
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}

	@Column (name="wildpitches", nullable=false)
	public int getWildPitches() {
		return wildPitches;
	}
	public void setWildPitches(int wildPitches) {
		this.wildPitches = wildPitches;
	}

	@Column (name="stolenbases", nullable=false)
	public int getStolenBases() {
		return stolenBases;
	}
	public void setStolenBases(int stolenBases) {
		this.stolenBases = stolenBases;
	}

	@Column (name="pickoffs", nullable=false)
	public int getPickoffs() {
		return pickoffs;
	}
	public void setPickoffs(int pickoffs) {
		this.pickoffs = pickoffs;
	}

	@Column (name="wins", nullable=false)
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}

	@Column (name="losses", nullable=false)
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}

	@Column (name="ties", nullable=false)
	public int getTies() {
		return ties;
	}
	public void setTies(int ties) {
		this.ties = ties;
	}

	@Column (name="saves", nullable=false)
	public int getSaves() {
		return saves;
	}
	public void setSaves(int saves) {
		this.saves = saves;
	}

	//CORRECT WITH FORMULA
	@Formula("case " +
			"   when innings = 0 then 0 " +
			"   else (earned_runs*9)/innings " +
			"end")
	public float getEarnedRunAverage(){ return earnedRunAverage; }
	public void setEarnedRunAverage(float earnedRunAverage) {
		this.earnedRunAverage = earnedRunAverage;
	}

	@Formula("case " +
			"   when innings = 0 then 0 " +
			"   else (hits+walks)/innings " +
			"end")
	public float getWalksAndHitsPerInning(){ return walksAndHitsPerInning;}
	public void setWalksAndHitsPerInning(float walksAndHitsPerInning){
		this.walksAndHitsPerInning = walksAndHitsPerInning;
	}
}
