package com.it.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Authority implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int authorityId;
    private int authorityAdminId;
    private int authorityResourceId;
    private Date authorityAddTime;
    private String authorityIp;
}
