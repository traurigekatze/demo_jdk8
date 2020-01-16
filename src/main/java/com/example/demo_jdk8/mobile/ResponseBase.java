package com.example.demo_jdk8.mobile;

import java.util.HashMap;
import java.util.Map;

public class ResponseBase {

    /**
     * response
     * @param code
     * @param msg
     * @return
     */
    public static Map<String, String> response(String code, String msg) {
        return new HashMap<String, String>() {
            {
                put(code, msg);
            }
        };
    }

}
