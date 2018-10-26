package com.example.demo_jdk8.main;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串格式化
 * @author hekai
 *
 */
@Slf4j
public class StringFormatTest {
	
	public static void main(String[] args) {
		String str = "test{s}and{c}";
		Map<String, Object> map = new HashMap<>();
		map.put("s", "s");
		map.put("c", "c");
		str = substitute(str, map);
		System.out.println(str);
	}

    /**
     * 格式化字符串
     * @param tepl
     * @param params
     * @return
     */
    public static String substitute(String tepl, Map<String, Object> params) {
        String text = tepl;
        for(Map.Entry<String, Object> entry:params.entrySet()){
            String key = entry.getKey();
            text = text.replaceAll("\\{" + key + "}", safeRegexReplacement(String.valueOf(entry.getValue())));
        }
        return text;
    }

    private static String safeRegexReplacement(String replacement) {
        if (StringUtils.isBlank(replacement)) {
            return replacement;
        }
        log.info("replacement = {}", replacement);
        return replacement.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$");
    }
    
}
