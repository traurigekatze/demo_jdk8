package com.example.demo_jdk8.mobile;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class MobileReq {

    private String tableName;

    private String family;

    public boolean isBlank() {
        return StringUtils.isBlank(tableName) || StringUtils.isBlank(family);
    }

}
