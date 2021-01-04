package com.test.config;

import com.ibm.mq.*;

import java.io.IOException;
 
public class MQSample{
    //定义队列管理器和远程队列的名称
    // MQ服务器的IP地址
    private static String hostname = "91.20.2.211";
    // MQ端口
    private static int port = 2727;
    // MQ的队列管理器名称;
    private static String qmName = "C_Test";
    // 服务器连接的通道
    private static String channel = "C_Test";
    // MQ远程队列的名称;
    private static String qName = "RQ_00";
    static String userId = "CHENP";
    static String password = "123";
    
    private static MQQueueManager qMgr;
 
    public static void main(String args[]) {
        try {
            MQEnvironment.hostname = hostname;
            MQEnvironment.port = port;
            MQEnvironment.channel = channel;
            MQEnvironment.userID = userId;
            MQEnvironment.password = password;
            
            // Create a connection to the queue manager 
            qMgr = new MQQueueManager(qmName);
            // Set up the options on the queue we wish to open... 
            int openOptions = 16;
            // Now specify the queue that we wish to open, 
            // and the open options... 
            MQQueue remoteQ = qMgr.accessQueue(qName, openOptions); 
            
            // Define a simple WebSphere MQ message, and write some text in UTF format.. 
            MQMessage putMessage = new MQMessage(); 
            putMessage.writeUTF("Test"); 
            // specify the message options... 
            MQPutMessageOptions pmo = new MQPutMessageOptions(); 
            // accept the defaults, same as MQPMO_DEFAULT
            // put the message on the queue 
            remoteQ.put(putMessage,pmo); 
            System.out.println("Message has been input into the Remote Queue");
 
            // Close the queue... 
            remoteQ.close(); 
            // Disconnect from the queue manager 
            qMgr.disconnect(); 
        }catch (MQException ex) { 
            // If an error has occurred in the above, try to identify what went wrong 
            // Was it a WebSphere MQ error?
            ex.printStackTrace();
            System.out.println("A WebSphere MQ error occurred : Completion code " + ex.completionCode + 
          " Reason code " + ex.reasonCode); 
        }catch (IOException ex) { 
            // Was it a Java buffer space error?
            ex.printStackTrace();
            System.out.println("An error occurred whilst writing to the message buffer: " + ex); 
        }catch(Exception ex){
        ex.printStackTrace();
    }
    }
}