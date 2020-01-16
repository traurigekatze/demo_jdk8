package com.example.demo_jdk8.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

@Slf4j
public class HBase {

    private static final String TABLE_NAME = "card_number_info";
    private static final String CF_DEFAULT = "info"; // table_description
    public static final byte[] QUALIFIER1 = "idCard".getBytes();
    public static final byte[] QUALIFIER2 = "cipherText".getBytes();
    private static final byte[] ROWKEY = "998".getBytes();

    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        String zkAddress = "hb-proxy-pub-uf6ipm2ixau5oq4u2-master2-001.hbase.rds.aliyuncs.com:2181,hb-proxy-pub-uf6ipm2ixau5oq4u2-master1-001.hbase.rds.aliyuncs.com:2181,hb-proxy-pub-uf6ipm2ixau5oq4u2-master3-001.hbase.rds.aliyuncs.com:2181";
        config.set(HConstants.ZOOKEEPER_QUORUM, zkAddress);
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(config);

            Long startTime = System.currentTimeMillis();
            Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
            try {

                Get get = new Get(ROWKEY);
                Result r = table.get(get);
                byte[] idCard = r.getValue(CF_DEFAULT.getBytes(), QUALIFIER1);  // returns current version of value
                byte[] cipherText = r.getValue(CF_DEFAULT.getBytes(), QUALIFIER2);  // returns current version of value

                log.info("idCard：{}, cipherText：{}", new String(idCard), new String(cipherText));
                log.info("use time：{}", System.currentTimeMillis() - startTime);
            } finally {
                if (table != null) table.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
