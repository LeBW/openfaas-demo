package com.faas.javatmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author LBW
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {
    private final static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();

        String requestStr = request.getParameterMap().toString();
        ActiveSpan.tag("params", requestStr);
        log.info("url:{} , method:{}, clientIp:{}, params:{}", uri, method, remoteAddr, requestStr);
        return true;
    }
}
