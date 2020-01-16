package com.example.demo_jdk8.idcard;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class IdCardReq {

    private String startDate;

    private String endDate;

    private List<String> areas;

    private List<String> excludeAreas;

    private String tableName;

    private String family;

    public String[] getStartDates() {
        return startDate.split("-");
    }

    public String[] getEndDates() {
        return startDate.split("-");
    }

    public boolean isBlank() {
        return StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate) || StringUtils.isBlank(tableName) || StringUtils.isBlank(family);
    }

}
