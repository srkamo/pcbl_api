package com.main.pcblroyals.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity(name = "players")
@Table (name="players", schema="pcblroyals_dev")
public class Player implements Comparable<Player> {
    private int id;
	private String firstName;
	private String lastName;
	private int jerseyNumber;
	private String positions;
	private String throwSide;
	private String batSide;
    @JsonIgnore
    private List<Season> seasons;

    @Column (name="bats", nullable=false)
    public String getBatSide() {
		return batSide;
	}
	public void setBatSide(String batSide) {
		this.batSide = batSide;
	}

	@Column (name="first_name", nullable=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column (name="jersey_number", nullable=false)
	public int getJerseyNumber() {
		return jerseyNumber;
	}
	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	@Column (name="last_name", nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column (name="positions", nullable=false)
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}

	@Column (name="throws", nullable=false)
	public String getThrowSide() {
		return throwSide;
	}
	public void setThrowSide(String throwSide) {
		this.throwSide = throwSide;
	}

    @ManyToMany(
        targetEntity=Season.class,
        fetch=FetchType.EAGER,
        cascade={CascadeType.MERGE}
    )
    @JoinTable(
        name="player_season",
        joinColumns={@JoinColumn(name="player_id")},
        inverseJoinColumns={@JoinColumn(name="season_id")}
    )
    public List<Season> getSeasons() {
		return seasons;
	}
	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	@Transient
	public String getFullNameLastFirst() {
		return getLastName() + ", " + getFirstName();
	}

    public int compareTo(Player p) {
		return getFullNameLastFirst().compareTo(p.getFullNameLastFirst());
	}
}
