package com.test;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Jasyptjiami {

    @Test
    public void jiami(){
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        basicTextEncryptor.setPassword("123456");
        //对family.father.name进行加密
        String s = basicTextEncryptor.encrypt("happy");
        System.err.println(s);
        String ss = basicTextEncryptor.decrypt(s);
        System.err.println(ss);
    }

    @Test
    public void tes5(){
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");//加密方式，默认PBEWithMD5AndDES，可改PBEWithMD5AndTripleDES
        config.setPassword("123456");//加密所需的salt(盐)
        //应用配置
        encryptor.setConfig(config);

        //对family.father.name进行加密
        String s = encryptor.encrypt("huang");
        System.err.println(s);  //aOWPzw1WXvSt19BGQd5VMA==

        String ss = encryptor.decrypt("JTi8czYa/4vDBc3bSrXD9A==");
        String sss = encryptor.decrypt(s);
        System.err.println(ss);
        System.err.println(sss);
    }

    @Test
    public void test (){
        Calendar c = Calendar.getInstance();
        //测试  改为s.parse("2020-10-28")
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        c.add(Calendar.YEAR, -1);
        System.err.println(c.get(Calendar.YEAR));
    }

}
