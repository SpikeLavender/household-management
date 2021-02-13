package com.natsumes.edu.interceptor;

import com.natsumes.edu.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.natsumes.edu.config.HouseholdConst.CURRENT_USER;

/**
 * @author hetengjiao
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * true 表示流程继续, false 表示中断
     *
     * @param request request
     * @param response response
     * @param handler handler
     * @return true or false
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        UserInfo user = (UserInfo) request.getSession().getAttribute(CURRENT_USER);
        if (user == null) {
            log.info("user = null");
            throw new AuthenticationException("认证失败");
        }
        return true;
    }
}
