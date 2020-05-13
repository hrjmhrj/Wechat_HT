package com.aisino.util.jwt;

import com.aisino.entity.cxcf.NormalException;
import com.aisino.entity.cxcf.ReturnJsonNoData;
import com.aisino.entity.cxcf.StaticData;
import com.aisino.util.handledata.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * token简易版本
 */
@RestController
public class JwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static final String sign = "HangXinAuthv1.0.0";
    public static final int jiXuShiYong = 3600;
    public static final int maxShiYongToken = 7200000 * 12;


    public static Map<String, String> tokenMap = new HashMap<>();

    @RequestMapping("/getToken")
    public Object getToken(String username, String password) {
        try {
            ReturnJsonNoData result = new ReturnJsonNoData();

            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                // 请输入正确的用户名和密码！
                result = new ReturnJsonNoData(1, "请输入正确的用户名和密码!");
            } else {
                // 查询用户名密码是否正确
                List<HashMap<String, String>> list = new ArrayList<>();
                list.add(new HashMap<>());

                // 如果数据库没有查找到数据
                if (list.size() == 0) {
                    // 用户名或密码错误！
                    result = new ReturnJsonNoData(1, "用户名或密码错误!");
                } else {
                    // 返回token值
                    String tokenStr = "";

                    // 查看公共map是否存有token
                    if (!tokenMap.containsKey(username)) {
                        tokenStr = getTokenString(username);
                        tokenMap.put(username, tokenStr);
                    } else {
                        // 判断token是否过去
                        tokenStr = tokenMap.get(username);
                        // 获取jwt实体对象接口实例
                        final Claims claims = Jwts.parser().setSigningKey(JwtUtil.sign)
                                .parseClaimsJws(tokenStr).getBody();
                        // 获取时间
                        Date date = claims.getExpiration();
                        long dbBuildTime = date.getTime();

                        // 当前时间
                        long currentTime = System.currentTimeMillis();

                        // 如果还有一个小时可以继续使用
                        long second = TimeUnit.MILLISECONDS.toSeconds(dbBuildTime - currentTime);
                        if (second > jiXuShiYong) {
                            tokenStr = tokenMap.get(username);
                        } else {
                            // 生成newToken
                            tokenStr = getTokenString(username);
                            // 插入
                            tokenMap.put(username, tokenStr);
                        }
                    }
                    // 设置返回json
                    result = new ReturnJsonNoData(0, tokenStr);
                }
            }
            return result;
        } catch (NormalException e) {
            LOGGER.error(StaticData.ziDingTiShi, e);
            return new ReturnJsonNoData(1, e.getMessage());
        } catch (Exception e) {
            LOGGER.error(StaticData.exceptionTiShi, e);
            return new ReturnJsonNoData(1, "生成token出错");
        }
    }


    public static String getTokenString(String username) {
        // 获取当前时间
        Date now = new Date(System.currentTimeMillis());
        // 过期时间
        Date expiration = new Date(now.getTime() + maxShiYongToken);
        return Jwts
                .builder()
                .setSubject(username)
//                 .claim(YAuthConstants.Y_AUTH_ROLES, userDbInfo.getRoles())
                .setIssuedAt(now).setIssuer("Online YAuth Builder")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, sign)
                .compact();
    }

    /**
     * 安全生成uuid方法
     *
     * @return
     */
    public static String getUUID() {
        String s = "";
        synchronized (s) {
            s = java.util.UUID.randomUUID().toString();
        }
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
                + s.substring(19, 23) + s.substring(24);
    }

    public static void main(String[] args) {
        System.out.println(getUUID().length());
    }
}
