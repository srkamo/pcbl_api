package com.main.pcblroyals.data;

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

@Entity(name="teams")
@Table (name="teams", schema="pcblroyals_dev")
public class Team implements Comparable<Team> {
    private int id;
    private String name;
    private List<Season> seasons;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column (name="name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    @ManyToMany(
        targetEntity=Season.class,
        fetch=FetchType.LAZY,
        cascade={CascadeType.MERGE}
    )
    @JoinTable(
        name="team_season",
        joinColumns={@JoinColumn(name="team_id")},
        inverseJoinColumns={@JoinColumn(name="season_id")}
    )
    public List<Season> getSeasons() {
		return seasons;
	}
	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

    public int compareTo(Team t) {
		return getName().compareTo(t.getName());
	}
}

