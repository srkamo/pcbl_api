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

@Entity(name = "pitching_stats")
@Table (name="pitching_stats", schema="pcblroyals_dev")
public class PitchingStat {
	private int id;
    private Player player;
    private Game game;
    private double innings;
    private int earnedRuns;
    private int totalRuns;
    private int strikeouts;
    private int walks;
    private int hitByPitch;
    private int hits;
    private int wildPitches;
    private int stolenBases;
    private int pickoffs;
    private int result;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name="game_id", nullable=false)
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	@ManyToOne
    @JoinColumn(name="player_id", nullable=false)
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Column (name="innings", nullable=false)
	public double getInnings() {
		return innings;
	}
	public void setInnings(double innings) {
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

	@Column (name="result", nullable=false)
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

}
