package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by rblay on 10/23/18.
 */
public class SingleSeasonPitchingRecordBean implements Serializable {
    protected String recordString;
    protected String seasonString;
    protected String recordName;
    protected BigDecimal recordValue;
    protected String roundedValue;

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

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public BigDecimal getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(BigDecimal recordValue) {
        this.recordValue = recordValue;
    }

    public String getRoundedValue() {
        return roundedValue;
    }

    public void setRoundedValue(String roundedValue) {
        this.roundedValue = roundedValue;
    }

    protected void calculateRoundedValue(){
        DecimalFormat df = new DecimalFormat("0.00");
        roundedValue = df.format(recordValue);
    }

    public SingleSeasonPitchingRecordBean(String recordString, String seasonString, String recordName, BigDecimal recordValue) {
        this.recordString = recordString;
        this.seasonString = seasonString;
        this.recordName = recordName;
        this.recordValue = recordValue;
        calculateRoundedValue();
    }
}
