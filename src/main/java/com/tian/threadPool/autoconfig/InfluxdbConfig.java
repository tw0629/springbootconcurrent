package com.tian.threadPool.autoconfig;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxdbConfig {

    @Value("${influxDB.serverAddr}")
    private String serverAddr;
    @Value("${influxDB.username}")
    private String username;
    @Value("${influxDB.password}")
    private String password;
    @Value("${influxDB.dataBase}")
    private String dataBase;
    @Value("${influxDB.retentionPolicyName}")
    private String retentionPolicyName;
    @Value("${influxDB.retentionPolicy}")
    private String retentionPolicy;

    @Bean
    public InfluxDB influxDB() {
        // 连接influxDB数据库
        InfluxDB influxDB = InfluxDBFactory.connect(serverAddr, username, password);
        // 创建数据库
        influxDB.query(new Query("CREATE DATABASE " + dataBase));
        influxDB.setDatabase(dataBase);
        // 创建数据保留策略
        influxDB.query(new Query("CREATE RETENTION POLICY \"" + retentionPolicyName + "\" ON \"" + dataBase
                + "\" DURATION " + retentionPolicy + " REPLICATION 1 DEFAULT"));
        influxDB.setRetentionPolicy(retentionPolicyName);
        return influxDB;
    }
}
