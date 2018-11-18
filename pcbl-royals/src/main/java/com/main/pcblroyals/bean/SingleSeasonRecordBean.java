package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rblay on 10/24/18.
 */
public class SingleSeasonRecordBean extends RecordBean implements Serializable {
    protected String seasonString;

    public String getSeasonString() {
        return seasonString;
    }
    public void setSeasonString(String seasonString) {
        this.seasonString = seasonString;
    }

    public SingleSeasonRecordBean(String recordString, String recordName, String recordValue, String seasonString, int player_id) {
        this.recordString = recordString;
        this.recordName = recordName;
        this.recordValue = recordValue;
        this.seasonString = seasonString;
        this.player_id = player_id;
    }
}
