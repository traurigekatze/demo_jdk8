package com.example.demo_jdk8.mobile;

import com.example.demo_jdk8.hbase.HBaseUtil;
import com.example.demo_jdk8.util.MD5Util;
import com.example.demo_jdk8.util.Sm3Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.demo_jdk8.idcard.IdCardProduct.fillStr;

@Slf4j
@Component
public class MobileProduct {

    private static List<String> area_infos = new ArrayList<>();

    private static List<String> area_codes = new ArrayList<>();

    /**
     * init area code
     */
    private static void initData() {
        // 1：init file
        File csv = new File("C:\\Users\\traur\\Desktop\\test\\Mobile.csv1810.csv");
        try{
            // 2：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            // 3：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                area_infos.add(lineDta);
            }
            textFile.close();
        } catch (FileNotFoundException e) {
            log.info("file not exists");
        } catch (IOException e) {
            log.info("file read error");
        }
        log.info("area_infos.size：{}", area_infos.size());
        area_infos.subList(384800, 384900).forEach( i -> {
            area_codes.add(i.split(",")[1]);
            return;
        });
    }

    /**
     * generate mobile
     * @param codeInfo
     * @return
     * @throws UnsupportedEncodingException
     */
    public static List<Mobile> generateMobile(String codeInfo) throws UnsupportedEncodingException {
        List<Mobile> mobiles = new ArrayList<>();
        for (int i = 1; i < 10000; i++) {
            String cisCode = fillStr(String.valueOf(i), 4);
            String mobile = codeInfo + cisCode;
            String md5 = MD5Util.encryption(mobile);
            String sha = DigestUtils.sha1Hex(mobile);
            String sm3Secret = Sm3Utils.encrypt(mobile);
            mobiles.add(Mobile.builder().mobile(mobile).cipherText(MD5Util.encryption(md5+sha)).sm3Secret(sm3Secret).build());
        }
        return mobiles;
    }

    /**
     * product mobile && insert HBase
     * @param mobileReq
     * @throws IOException
     */
    @Async
    public void product(MobileReq mobileReq) throws IOException {
        HBaseUtil.connect();
        HBaseUtil.createTable(mobileReq.getTableName(), mobileReq.getFamily(), false);
        long startTime = System.currentTimeMillis();
        AtomicLong size = new AtomicLong(0L);
        area_codes.forEach(code -> {
            try {
                List<Mobile> mobiles = generateMobile(code);
                size.addAndGet((long) mobiles.size());
                List<Mobile> list = new ArrayList<>();
                for (int i = 0; i < mobiles.size(); i++) {
                    list.add(mobiles.get(i));
                    if (list.size() == 3000 || i == mobiles.size() - 1) {
                        HBaseUtil.insertMobiles(mobileReq.getTableName(), mobileReq.getFamily(), list);
                        list.clear();
                    }
                }
            } catch (UnsupportedEncodingException e) {
                log.info("generateMobile error：{}", e.getMessage(), e);
            } catch (IOException e) {
                log.info("insert mobile error：{}", e.getMessage(), e);
            }
        });
        HBaseUtil.close();
        log.info("use time：{}，size：{}", (System.currentTimeMillis() - startTime), size);
        long useTime = System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        initData();
        log.info("area_codes.size：{}", area_codes.size());
        List<Mobile> mobiles = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        area_codes.forEach(code -> {
            try {
                mobiles.addAll(generateMobile(code));
            } catch (UnsupportedEncodingException e) {
                log.info("generateMobile error：{}", e.getMessage(), e);
            }
        });
        log.info("mobiles.size：{}，useTime：{}", mobiles.size(), (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        boolean contain = false;
        for (Mobile mobile : mobiles) {
            contain = mobile.mobile.equals("18883700559");
            if (contain)
                break;
        }
        log.info("exists：{}，useTime：{}", contain, (System.currentTimeMillis() - startTime));
    }

}
