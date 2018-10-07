package com.main.pcblroyals.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "games")
@Table (name="games", schema="pcblroyals_dev")
public class Game implements Comparable<Game> {
    private int id;
    private Season season;
    private Team opponent;
    private boolean homeTeam;
    private boolean playoff;
    private Date date;
    private String startTime;
    private int teamScore;
    private int opponentScore;
    private String location;

	@Column (name="date", nullable=false)
    public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Column (name="home_team", nullable=false)
	public boolean isHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(boolean homeTeam) {
		this.homeTeam = homeTeam;
	}

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column (name="location", nullable=false)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

    @ManyToOne
    @JoinColumn(name="opponent_team_id", nullable=false)
	public Team getOpponent() {
		return opponent;
	}
	public void setOpponent(Team opponent) {
		this.opponent = opponent;
	}

	@Transient
	public String getOpponentTeamInfo() {
		if (isHomeTeam()) {
			return "vs. " + opponent.getName();
		} else {
			return "at " + opponent.getName();
		}
	}

	@Column (name="opponent_score", nullable=false)
	public int getOpponentScore() {
		return opponentScore;
	}
	public void setOpponentScore(int opponentScore) {
		this.opponentScore = opponentScore;
	}

	@Column (name="playoff", nullable=false)
	public boolean isPlayoff() {
		return playoff;
	}
	public void setPlayoff(boolean playoff) {
		this.playoff = playoff;
	}

    @ManyToOne
    @JoinColumn(name="season_id", nullable=false)
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}

	@Column (name="start_time", nullable=false)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column (name="team_score", nullable=false)
	public int getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(int teamScore) {
		this.teamScore = teamScore;
	}

    public int compareTo(Game g) {
    	if (getDate().compareTo(g.getDate()) == 0) {
    		return g.getStartTime().compareTo(getStartTime());
    	}
		return g.getDate().compareTo(getDate());
	}

}
