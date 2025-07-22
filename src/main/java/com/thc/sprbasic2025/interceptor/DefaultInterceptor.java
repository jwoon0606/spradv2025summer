package com.thc.sprbasic2025.interceptor;

import com.thc.sprbasic2025.constants.AuthConstants;
import com.thc.sprbasic2025.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
public class DefaultInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    final TokenFactory tokenFactory;

    //컨트롤러 진입 전에 호출되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader(AuthConstants.HEADER_STRING);
        logger.info("preHandle / 1 accessToken [{}]", accessToken);

        Long userId = null;
        if(accessToken != null && accessToken.startsWith(AuthConstants.TOKEN_PREFIX)){
            accessToken = accessToken.substring(AuthConstants.TOKEN_PREFIX.length());
            logger.info("preHandle / 2 accessToken [{}]", accessToken);
            userId = tokenFactory.validateKey(accessToken);
        }
        request.setAttribute("reqUserId", userId);
        return true;
    }

    //컨트롤러 실행 후에 호출되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle / request [{}]", request);
    }

    //모든것을 마친 후 실행되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion / request [{}]", request);
    }

}
