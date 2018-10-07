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

@Entity(name = "batting_career_stats")
@Table (name="batting_career_stats", schema="pcblRoyals_dev")
public class CareerBattingStat {
	private int id;
    private Player player;
    private int games;
    private int atBats;
    private int singles;
    private int doubles;
    private int triples;
    private int homeRuns;
    private int walks;
    private int hitByPitch;
    private int sacrifices;
    private int runs;
    private int rbis;
    private int stolenBases;
    private int passedBalls;
    private int caughtStealing;
    private int strikeOuts;

	private float battingAverage;
	private float onBasePercentage;
    private float sluggingAverage;


    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column (name="games", nullable=false)
	public int getGames() {
		return games;
	}
	public void setGames(int games) {
		this.games = games;
	}

	@Column (name="atbats", nullable=false)
	public int getAtBats() {
		return atBats;
	}
	public void setAtBats(int atBats) {
		this.atBats = atBats;
	}

	@Column (name="caughtstealing", nullable=false)
	public int getCaughtStealing() {
		return caughtStealing;
	}
	public void setCaughtStealing(int caughtStealing) {
		this.caughtStealing = caughtStealing;
	}

	@Column (name="doubles", nullable=false)
	public int getDoubles() {
		return doubles;
	}
	public void setDoubles(int doubles) {
		this.doubles = doubles;
	}

	@Column (name="hitbypitch", nullable=false)
	public int getHitByPitch() {
		return hitByPitch;
	}
	public void setHitByPitch(int hitByPitch) {
		this.hitByPitch = hitByPitch;
	}

	@Column (name="homeruns", nullable=false)
	public int getHomeRuns() {
		return homeRuns;
	}
	public void setHomeRuns(int homeRuns) {
		this.homeRuns = homeRuns;
	}

	@Column (name="passedballs", nullable=false)
	public int getPassedBalls() {
		return passedBalls;
	}
	public void setPassedBalls(int passedBalls) {
		this.passedBalls = passedBalls;
	}

    @ManyToOne
    @JoinColumn(name="player_id", nullable=false)
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Column (name="rbis", nullable=false)
	public int getRbis() {
		return rbis;
	}
	public void setRbis(int rbis) {
		this.rbis = rbis;
	}

	@Column (name="runs", nullable=false)
	public int getRuns() {
		return runs;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}

	@Column (name="sacrifices", nullable=false)
	public int getSacrifices() {
		return sacrifices;
	}
	public void setSacrifices(int sacrifices) {
		this.sacrifices = sacrifices;
	}

	@Column (name="singles", nullable=false)
	public int getSingles() {
		return singles;
	}
	public void setSingles(int singles) {
		this.singles = singles;
	}

	@Column (name="stolenbases", nullable=false)
	public int getStolenBases() {
		return stolenBases;
	}
	public void setStolenBases(int stolenBases) {
		this.stolenBases = stolenBases;
	}

	@Column (name="strikeouts", nullable=false)
	public int getStrikeOuts() {
		return strikeOuts;
	}
	public void setStrikeOuts(int strikeOuts) {
		this.strikeOuts = strikeOuts;
	}

	@Column (name="triples", nullable=false)
	public int getTriples() {
		return triples;
	}
	public void setTriples(int triples) {
		this.triples = triples;
	}

	@Column (name="walks", nullable=false)
	public int getWalks() {
		return walks;
	}
	public void setWalks(int walks) {
		this.walks = walks;
	}

	@Formula("case when atbats = 0 then 0 else round(singles/atbats,3) end")
	public float getBattingAverage(){ return battingAverage;}
	public void setBattingAverage(float battingAverage){ this.battingAverage = battingAverage;}

	@Formula("case " +
			"	when (atbats + walks + hitbypitch + sacrifices = 0) then 0 " +
			" 	else round((singles + walks + hitbypitch)/(atbats + walks + hitbypitch + sacrifices),3)" +
			"end")
	public float getOnBasePercentage(){ return onBasePercentage;}
	public void setOnBasePercentage(float onBasePercentage){ this.onBasePercentage = onBasePercentage;}

    @Formula("case " +
            "   when atbats = 0 then 0 " +
            "   else round((singles + 2*doubles + 3*triples + 4*homeruns)/(atbats),3) " +
            "end")
    public float getSluggingAverage(){ return sluggingAverage;}
    public void setSluggingAverage(float sluggingAverage){ this.sluggingAverage = sluggingAverage;}

}
