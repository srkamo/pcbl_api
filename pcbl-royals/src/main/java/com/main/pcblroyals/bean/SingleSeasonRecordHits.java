package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rblay on 10/21/18.
 */
public class SingleSeasonRecordHits implements Serializable {
    protected String recordString;
    protected String seasonString;
    protected BigDecimal hits;


    public String getRecordString() {
        return recordString;
    }

    public void setRecordString(String recordString) {
        this.recordString = recordString;
    }

    public String getSeasonString() {
        return seasonString;
    }

    public void setSeasonString(String seasonString) {
        this.seasonString = seasonString;
    }

    public BigDecimal getHits() {
        return hits;
    }

    public void setHits(BigDecimal hits) {
        this.hits = hits;
    }

    public SingleSeasonRecordHits(String recordString, String seasonString, BigDecimal hits) {
        this.recordString = recordString;
        this.seasonString = seasonString;
        this.hits = hits;
    }
}
