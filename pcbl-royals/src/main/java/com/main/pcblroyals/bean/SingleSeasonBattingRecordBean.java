package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by rblay on 10/23/18.
 */
public class SingleSeasonBattingRecordBean implements Serializable {
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

    public String getRoundedValue() {
        return roundedValue;
    }

    public void setRoundedValue(String roundedValue) {
        this.roundedValue = roundedValue;
    }

    public void setRecordValue(BigDecimal recordValue) {
        this.recordValue = recordValue;
    }

    protected void calculateRoundedValue(){
        DecimalFormat df = new DecimalFormat("0.000");
        roundedValue = df.format(recordValue);
    }

    public SingleSeasonBattingRecordBean(String recordString, String seasonString, String recordName, BigDecimal recordValue) {
        this.recordString = recordString;
        this.seasonString = seasonString;
        this.recordName = recordName;
        this.recordValue = recordValue;
        calculateRoundedValue();
    }
}
