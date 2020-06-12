package com.aisino;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @ClassName test
 * @Date 2020/4/3
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class test {

    @Test
    public void testredis() {
        //Jedis jedis = new Jedis("39.97.74.131", 6379);
        //String aa = jedis.get("a");
        //System.out.println(aa);
    }


}
