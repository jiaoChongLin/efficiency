package com.efficiency.entity;

import lombok.Data;

/**
 * @Author : Vincent.jiao
 * @Date : 2021/7/18 16:55
 * @Version : 1.0
 */
@Data
public class ConnInfo {
    public DataBaseDalect dataBaseDalect;
    private String username;
    private String password;
    private String url;

    public String getConnIdentifier(){
        return dataBaseDalect.value + ":" + username + ":" + password + ":" + url;
    }
}
