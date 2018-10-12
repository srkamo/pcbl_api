package com.main.pcblroyals.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import java.util.List;

import javax.persistence.*;


@Entity(name = "seasons")
@Table (name="seasons", schema="pcblRoyals_dev")
public class Season {
    private int id;
    private String season;
    private int year;
    private String division;

	//NEW CODE: Adding games in order to get season record
	private int wins;
	private int ties;
	private int losses;
	//END NEW CODE: Adding games in order to get record

	@JsonIgnore
	private List<Player> players;

    @ManyToMany(
        cascade={CascadeType.ALL},
        mappedBy="seasons",
        fetch=FetchType.EAGER,
        targetEntity=Player.class
    )
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	//NEW CODE: Adding games in order to get season record
	@Formula("(select sum(case when g.team_score > g.opponent_score then 1 else 0 end) from games g where g.season_id = id)")
	public int getWins(){ return wins;}
	public void setWins(int wins){ this.wins = wins;}

	@Formula("(select sum(case when g.team_score = g.opponent_score then 1 else 0 end) from games g where g.season_id = id)")
	public int getTies(){ return ties;}
	public void setTies(int ties){ this.ties = ties;}

	@Formula("(select sum(case when g.team_score < g.opponent_score then 1 else 0 end) from games g where g.season_id = id)")
	public int getLosses(){ return losses;}
	public void setLosses(int losses){ this.losses = losses;}
	//END NEW CODE: Adding games in order to get record

	@Column (name="division", nullable=false)
    public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column (name="season", nullable=false)
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}

	@Column (name="year", nullable=false)
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}