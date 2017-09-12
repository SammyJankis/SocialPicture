package com.arturoguillen.socialpicture.entities.client.twitter;

import java.io.Serializable;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Type {
        TWITTER,
        FACEBOOK
    }

    private Type authType;
    private String authToken;

    public void setAuthType(Type authType) {
        this.authType = authType;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
