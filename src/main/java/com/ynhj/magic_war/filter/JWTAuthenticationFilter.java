package com.ynhj.magic_war.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.BaseException;
import com.ynhj.magic_war.common.exception.SystemErrorType;
import com.ynhj.magic_war.model.entity.JwtUser;
import com.ynhj.magic_war.model.entity.dto.LoginRequest;
import com.ynhj.magic_war.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: JWTAuthenticationFilter
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        LoginRequest loginUser;
        try {
            // 从输入流中获取到登录的信息
            loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);


        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
                );
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        String token = JwtUtil.createToken(jwtUser);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader("token", JwtUtil.TOKEN_PREFIX + token);
        Result result = Result.success(JwtUtil.TOKEN_PREFIX + token);
        response.getWriter().println(JSON.toJSONString(result));
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
       // response.getWriter().write("authentication failed, reason: " + failed.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.fail("401","用户名或密码错误")));
    }
}
