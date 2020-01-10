package com.example.demo_jdk8.idcard;

import com.example.demo_jdk8.util.DateUtils;
import com.example.demo_jdk8.util.MD5Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class IdCardProduct {

    static List<Integer> factors = new ArrayList<>();
    static int scale = 11;
    static Map<Integer, String> ckCodeMap = new HashMap<>();

    static String filePath = "C:\\Users\\traur\\Desktop\\test\\idCard.txt";

    static {
        factors.add(7);
        factors.add(9);
        factors.add(10);
        factors.add(5);
        factors.add(8);
        factors.add(4);
        factors.add(2);
        factors.add(1);
        factors.add(6);
        factors.add(3);
        factors.add(7);
        factors.add(9);
        factors.add(10);
        factors.add(5);
        factors.add(8);
        factors.add(4);
        factors.add(2);

        ckCodeMap.put(0, "1");
        ckCodeMap.put(1, "0");
        ckCodeMap.put(2, "X");
        ckCodeMap.put(3, "9");
        ckCodeMap.put(4, "8");
        ckCodeMap.put(5, "7");
        ckCodeMap.put(6, "6");
        ckCodeMap.put(7, "5");
        ckCodeMap.put(8, "4");
        ckCodeMap.put(9, "3");
        ckCodeMap.put(10, "2");
    }

    public static void main(String[] args) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        LocalDate startDate = LocalDate.of(1990, 9, 5);
        LocalDate endDate = LocalDate.of(1990, 9, 6);
        List<LocalDate> dates = DateUtils.getDatesBetween(startDate, endDate);
        log.info("date list：{}", dates.size());
        long startTime = System.currentTimeMillis();
        dates.forEach(d -> {
            List<Entry> idCards = generateIdCard("500233", d.format(DateTimeFormatter.ofPattern(DateUtils.DATE_PATTERN)));
            idCards.forEach(k -> {
                try {
                    out.write("idCard：" + k.getIdCard() + ", MD5：" + k.getCipherText() + "\r\n"); // \r\n即为换行
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
        log.info("write is ok，file size：{}", file.length() / 1024 / 1024);
        log.info("use time：{}", (System.currentTimeMillis() - startTime));
    }

    /**
     * 生成 idCard
     * @param areaCode 区域码
     * @param birthday 出生日期
     * @return
     */
    public static List<Entry> generateIdCard(String areaCode, String birthday) {
        List<Entry> idCards = new ArrayList<>();
        // 区域码 + 出生日期 + 三位顺序码 + 校验码（加权因子计算得出）
        for (int i = 1; i < 1000; i++) {
            String idCard = areaCode + birthday;
            String cisCode = fillStr(String.valueOf(i), 3);
            idCard += cisCode;
            String ckCode = createCKCode(idCard.toCharArray(), factors, scale, ckCodeMap);
            idCard += ckCode;
            String md5 = MD5Util.encryption(idCard);
            String sha = DigestUtils.sha1Hex(idCard);
            idCards.add(Entry.builder().idCard(idCard).cipherText(MD5Util.encryption(md5+sha)).build());
        }
        return idCards;
    }

    /**
     * 填充顺位码
     * @param str 顺位码
     * @param len 长度
     * @return
     */
    public static String fillStr(String str, int len) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer(str);
        while (buffer.length() < len) {
            buffer.insert(0, "0");
        }
        return buffer.toString();
    }

    /**
     * 生成校验码
     * @param chars idCard
     * @param factors 加权因子
     * @param scale 取模值
     * @param ckCodeMap 余数对应校验码
     * @return
     */
    public static String createCKCode(char[] chars, List<Integer> factors, int scale, Map<Integer, String> ckCodeMap) {
        if (chars == null || CollectionUtils.isEmpty(factors) || chars.length != factors.size()) {
            return "";
        }
        Integer amount = 0;
        for (int i = 0; i < factors.size(); i++) {
            amount += Integer.parseInt(chars[i] + "") * factors.get(i);
        }
        int res = amount % scale;
        return ckCodeMap.get(res);
    }

}
