package com.aisino.util.jwt;


import com.aisino.util.handledata.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;


//@Controller
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o) throws Exception {
        LOGGER.info("当前拦截请求：" + request.getRequestURI());

        // 自动排除生成token的路径,并且如果是options请求是cors跨域预请求，设置allow对应头信息
        if (request.getRequestURI().equals("/getToken")
                || RequestMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        // 获取请求头中token
        String authHeader = request.getParameter("token");

        try {
            // 如果没有header信息
            if (StringUtils.isBlank(authHeader)) {
                throw new SignatureException("安全校验不通过！");
            }

            // 获取jwt实体对象接口实例
            final Claims claims = Jwts.parser().setSigningKey(JwtUtil.sign)
                    .parseClaimsJws(authHeader).getBody();
            // 获取用户名
            String username = claims.getSubject();
            // 校验数据库信息，由于密码暂缺，暂不考虑
            judgeToken(username, authHeader);
        } catch (SignatureException | ExpiredJwtException e) {
            // 输出对象
            PrintWriter writer = response.getWriter();
            // 输出error消息
            writer.write("token error!");
            writer.close();
            return false;
        } catch (Exception e) {
            // 输出对象
            PrintWriter writer = response.getWriter();
            // 输出error消息
            writer.write(e.getMessage());
            writer.close();
            return false;
        }
        return true;
    }

    /**
     * @throws
     * @Title: judgeToken
     * @Description: 需要验证数据库的token
     * @param: @param id
     * @param: @param authHeader
     * @return: void
     */
    public void judgeToken(String username, String authHeader) throws SignatureException {
        // 数据库中的token值
        String tokenval = null;
        if (JwtUtil.tokenMap.containsKey(username)) {
            tokenval = JwtUtil.tokenMap.get(username);
        }
        // 如果内存中不存在,提示客户端获取token
        if (tokenval == null || tokenval.trim() == "") {
            throw new SignatureException(
                    "token错误！");
        }
        // 判断内存中的token是否与客户端传来的一致
        if (!tokenval.equals(authHeader)) {
            throw new SignatureException(
                    "token错误！");
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {

    }

}