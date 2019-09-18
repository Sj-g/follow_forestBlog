package com.it.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int userId;
    private String userPass;
    private String userNickName;
    private String userEmail;
    private String userLastLoginIp;
    private Date userRegisterTime;
    private Date userLastLoginTime;
    private int userStatus;
}
