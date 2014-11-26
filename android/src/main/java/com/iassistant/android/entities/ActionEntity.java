package com.iassistant.android.entities;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * Created by lan on 11/25/14.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionEntity extends AbstractModule {
    private String action;
    private Date expireTime;
    private String status;
    private String target;
    private Date createTime;
    private Map<String, String> extra = Maps.newHashMap();

    public String addExtra(Extra k, String v) {
        return extra.put(k.name(), v);
    }

    public static enum Status {
        created,sent,retrieved,responded,finished
    }

    public static enum Extra {
        ip
    }
}
