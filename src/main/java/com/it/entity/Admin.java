package com.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int adminId;
    private String adminPass;
    private String adminNickName;
    private String adminEmail;
    private String adminLastLoginIp;
    private Date adminRegisterTime;
    private Date adminLastLoginTime;
    private int adminStatus;
    private int adminClass;
}
