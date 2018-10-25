package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rblay on 10/24/18.
 */
public class RecordBean implements Serializable {
    protected String recordString;
    protected String recordName;
    protected BigDecimal recordValue;

    public String getRecordString() {
        return recordString;
    }

    public void setRecordString(String recordString) {
        this.recordString = recordString;
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

    public RecordBean() {
    }

    public RecordBean(String recordString, String recordName, BigDecimal recordValue){
        this.recordString = recordString;
        this.recordName = recordName;
        this.recordValue = recordValue;
    }
}
