package com.main.pcblroyals.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import java.util.Comparator;
import java.util.List;

import javax.persistence.*;


@Entity(name = "seasons")
@Table (name="seasons", schema="pcblRoyals_dev")
public class Season implements Comparable<Season>{
    private int id;
    private String season;
    private int year;
    private String division;

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

	@Column (name="division", nullable=false)
    public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
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

	public int compareTo(Season s){
		return Comparator.comparingInt(Season::getYear).
				thenComparing((Season::getSeason)).reversed().
				thenComparing(Season::getId).compare(this,s);
	}
}