package com.example.demo_jdk8.hbase;

import com.example.demo_jdk8.idcard.IdCardInfo;
import com.example.demo_jdk8.mobile.Mobile;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * HBase util
 */
@Slf4j
public class HBaseUtil {

    public static final Configuration config;

    public static Connection connection = null;

    static {
        config = HBaseConfiguration.create();
        String zkAddress = "hb-proxy-pub-uf6ipm2ixau5oq4u2-master2-001.hbase.rds.aliyuncs.com:2181,hb-proxy-pub-uf6ipm2ixau5oq4u2-master1-001.hbase.rds.aliyuncs.com:2181,hb-proxy-pub-uf6ipm2ixau5oq4u2-master3-001.hbase.rds.aliyuncs.com:2181";
        config.set(HConstants.ZOOKEEPER_QUORUM, zkAddress);
    }

    public static void connect() throws IOException {
        connection = ConnectionFactory.createConnection(config);
    }

    /**
     * create table
     * @param tableName table name
     * @param family description && family
     * @return
     * @throws IOException
     */
    public static String createTable(String tableName, String family, boolean delete) throws IOException {
        Admin admin = connection.getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName)) && delete) {
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));

            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
            tableDescriptor.addFamily(new HColumnDescriptor(family));
            admin.createTable(tableDescriptor);
        }
        return tableName;
    }

    /**
     * insert idCardInfos
     * @param tableName
     * @param family
     * @param idCardInfos
     * @return
     * @throws IOException
     */
    public static String insertIdCards(String tableName, String family, List<IdCardInfo> idCardInfos) throws IOException {
        if (CollectionUtils.isEmpty(idCardInfos))
            return null;
        Table table = connection.getTable(TableName.valueOf(tableName));
        try {
            List<Put> puts = new ArrayList<>();
            for (int i = 0; i < idCardInfos.size(); i++) {
                Put put = new Put(idCardInfos.get(i).getCipherText().getBytes());
                put.addColumn(family.getBytes(), "idCard".getBytes(), idCardInfos.get(i).getIdCard().getBytes());
                put.addColumn(family.getBytes(), "cipherText".getBytes(), idCardInfos.get(i).getCipherText().getBytes());
                puts.add(put);
            }
            table.put(puts);
        } finally {
            if (table != null) table.close();
        }
        return idCardInfos.size() + "";
    }

    /**
     * inser mobile
     * @param tableName
     * @param family
     * @param mobiles
     * @return
     * @throws IOException
     */
    public static String insertMobiles(String tableName, String family, List<Mobile> mobiles) throws IOException {
        if (CollectionUtils.isEmpty(mobiles))
            return null;
        Table table = connection.getTable(TableName.valueOf(tableName));
        try {
            List<Put> puts = new ArrayList<>();
            for (int i = 0; i < mobiles.size(); i++) {
                Put put = new Put(mobiles.get(i).getCipherText().getBytes());
                put.addColumn(family.getBytes(), "mobile".getBytes(), mobiles.get(i).getMobile().getBytes());
                put.addColumn(family.getBytes(), "cipherText".getBytes(), mobiles.get(i).getCipherText().getBytes());
                puts.add(put);
            }
            table.put(puts);
        } finally {
            if (table != null) table.close();
        }
        return mobiles.size() + "";
    }

    public static void close() throws IOException {
        if (connection != null)
            connection.close();
    }

}
