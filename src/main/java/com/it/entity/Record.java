package com.it.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Record implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int recordId;
    private int recordResourceId;
    private int recordAdminId;
    private Date recordUpdateTime;
    private String recordIp;
}
