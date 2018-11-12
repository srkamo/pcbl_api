package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rblay on 10/24/18.
 */
public class SingleGameRecordBean extends RecordBean implements Serializable {
    protected String gameString;

    public String getGameString() {
        return gameString;
    }

    public void setGameString(String gameString) {
        this.gameString = gameString;
    }

    public SingleGameRecordBean(String recordString, String recordName, String recordValue, String gameString) {
        this.recordString = recordString;
        this.recordName = recordName;
        this.recordValue = recordValue;
        this.gameString = gameString;
    }
}
