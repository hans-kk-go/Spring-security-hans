package com.sangeng.filter;

import com.sangeng.domain.LoginUser;
import com.sangeng.domain.User;
import com.sangeng.utils.Jwt;
import com.sangeng.utils.RedisCache;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = httpServletRequest.getHeader("token");
        System.out.println(token);

        if (!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;

        }

        //解析token
        boolean expiration = Jwt.isExpiration(token);
        if (expiration){
            throw new RemoteException("token无效");
        }else {

        }
        Long userId = Jwt.getUserId(token);
        System.out.println(userId);


        //从redis中获取用户信息
        String redisKey = "login"+userId;
        LoginUser user = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(user)){
            throw new RemoteException("用户未登录");
        }


        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
