package com.it.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int userId;
    @NotNull
    private String userPass;
    @NotNull
    private String userNickName;
    @Email
    @NotNull
    private String userEmail;
    private String userLastLoginIp;
    private Date userRegisterTime;
    private Date userLastLoginTime;
    private int userStatus;
    private String userImg;
}
