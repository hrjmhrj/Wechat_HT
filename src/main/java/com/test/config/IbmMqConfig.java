package com.test.config;

import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
//@Configuration
public class IbmMqConfig {

    @Value("${ibm.host}")
    private String host;

    @Value("${ibm.port}")
    private Integer port;

    @Value("${ibm.channel}")
    private String channel;

    @Value("${ibm.queue_manager}")
    private String queue_manager;

    @Value("${ibm.userId}")
    private String userId;

    @Value("${ibm.password}")
    private String password;

    private String queue_name = "RQ_00";


    @Bean
    MQQueueManager mqQueueManager() {
        MQQueueManager queueManager = null;
        MQEnvironment.hostname = host;
        MQEnvironment.port = port;
        MQEnvironment.channel = channel;
        MQEnvironment.userID = userId;
        MQEnvironment.password = password;

        try {
            queueManager = new MQQueueManager(queue_manager);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建队列管理器失败");
            System.exit(-1);
        }

        return queueManager;
    }


    @Bean
    MQQueue mqQueue(MQQueueManager queueManager) {
        MQQueue putQueue = null;

        try {
            int openOptionsArg = CMQC.MQOO_OUTPUT|CMQC.MQOO_INPUT_AS_Q_DEF|CMQC.MQOO_INQUIRE;
            putQueue = queueManager.accessQueue(queue_name, openOptionsArg);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("连接队列失败");
            System.exit(-1);
        }

        return putQueue;
    }
}