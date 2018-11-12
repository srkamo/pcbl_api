package com.main.pcblroyals.bean;

import java.io.InterruptedIOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rblay on 11/11/18.
 */
public class SeasonPlayerBean implements Serializable {
    protected List<Integer> player_ids;
    protected int season_id;

    public List<Integer> getPlayer_ids() {
        return player_ids;
    }

    public void setPlayer_ids(List<Integer> player_ids) {
        this.player_ids = player_ids;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public SeasonPlayerBean(List<Integer> player_ids, int season_id) {
        this.player_ids = player_ids;
        this.season_id = season_id;
    }

    public SeasonPlayerBean() {
        this.player_ids = new ArrayList<Integer>();
        this.season_id = 0;
    }
}
