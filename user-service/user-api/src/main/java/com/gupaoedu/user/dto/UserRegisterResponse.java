package com.gupaoedu.user.dto;

import com.gupaoedu.user.abs.AbstractResponse;

import java.io.Serializable;

/**
 * 刘海飞

 */
public class UserRegisterResponse extends AbstractResponse implements Serializable{

    private static final long serialVersionUID = -7690077437344492561L;

    private Integer uid;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
