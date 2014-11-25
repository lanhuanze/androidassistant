package com.iassistant.android.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by lan on 11/25/14.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResult extends AbstractModule {
    private String email;
    private String phoneNumber;
    private Date createTime;
    private String authToken;
    private String accountId;
    private String deviceId;
}
