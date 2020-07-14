package com.upgrad.proman.service.type;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {

    REGISTERED(4), ACTIVE(3), INACTIVE(2), LOCKED(1), DELETED(0);

    private static final Map<Integer, UserStatus> lookup = new HashMap<>();

    static{
            for( UserStatus userStatus : UserStatus.values()){
                lookup.put(userStatus.getCode(), userStatus);
            }
    }

    private final int code;

    private UserStatus(final int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static UserStatus getEnum(final int code){
        return lookup.get(code);
    }
}
