package com.ale.crud.interceptor;

import com.ale.crud.bean.User;
import com.ale.crud.util.common.AuthenticationUtil;
import com.ale.crud.threadlocal.UserThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author alewu
 * @date 2017/11/1 17:17
 * @description 认证拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        // token不存在
        if (token != null) {
            User user = AuthenticationUtil.validateJWT(token, User.class);
            String userId = request.getParameter("userId");
            // 解密token后的userId与用户传来的userId不一致，大多是因为token过期
            if (user != null && userId != null && userId == user.getUserId()) {
                UserThreadLocal.setUser(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
