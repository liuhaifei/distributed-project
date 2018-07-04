package com.gupaoedu.user.dto;

import java.io.Serializable;

/**
 * 刘海飞

 */
public class CheckAuthRequest implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CheckAuthRequest{" +
                "token='" + token + '\'' +
                '}';
    }
}
