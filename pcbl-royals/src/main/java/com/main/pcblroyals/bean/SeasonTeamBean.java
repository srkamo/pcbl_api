package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rblay on 11/11/18.
 */
public class SeasonTeamBean implements Serializable {
    protected List<Integer> team_ids;
    protected int season_id;

    public List<Integer> getTeam_ids() {
        return team_ids;
    }

    public void setTeam_ids(List<Integer> team_ids) {
        this.team_ids = team_ids;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public SeasonTeamBean(List<Integer> team_ids, int season_id) {
        this.team_ids = team_ids;
        this.season_id = season_id;
    }

    public SeasonTeamBean() {
        this.team_ids = new ArrayList<Integer>();
        this.season_id = 0;
    }
}
