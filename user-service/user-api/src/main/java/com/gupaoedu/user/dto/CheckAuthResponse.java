package com.gupaoedu.user.dto;

import com.gupaoedu.user.abs.AbstractResponse;

/**
 * 刘海飞

 */
public class CheckAuthResponse extends AbstractResponse {

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CheckAuthResponse{" +
                "uid='" + uid + '\'' +
                "} " + super.toString();
    }
}
