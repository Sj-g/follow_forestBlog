package com.it.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Resource implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int resourceId;
    private String resourceName;
    private int resourceStatus;
    private int resourceOrder;
}
