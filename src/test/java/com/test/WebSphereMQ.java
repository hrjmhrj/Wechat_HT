package com.test;

import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSphereMQ {

    @Resource
    MQQueue putQueue;

    @Test
    public void putmsg() {
        String msg = "测试消息";
        MQMessage myMessage = new MQMessage();
        try {
            myMessage.writeUTF(msg);
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            putQueue.put(myMessage, pmo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getMsg() {
        MQMessage retrievedMessage = new MQMessage();

        MQGetMessageOptions gmo = new MQGetMessageOptions();
        try {
            putQueue.get(retrievedMessage, gmo);
            String msg = retrievedMessage.readUTF();
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
