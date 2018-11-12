package com.main.pcblroyals.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by rblay on 10/24/18.
 */
public class RecordBean implements Serializable {
    protected String recordString;
    protected String recordName;
    protected String recordValue;

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

    public String getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(String recordValue) {
        this.recordValue = recordValue;
    }

    public RecordBean() {
    }

    public RecordBean(String recordString, String recordName, String recordValue){
        this.recordString = recordString;
        this.recordName = recordName;
        this.recordValue = recordValue;
    }
}
