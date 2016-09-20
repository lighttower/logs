package com.icss.lighttower.logs.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.icss.lighttower.logs.entity.ConfigBean;
import com.icss.lighttower.logs.entity.Log;
import com.icss.lighttower.logs.service.LogService;

/**
 * 日志拦截器
 * 
 * @author Administrator
 *
 */
public class LogInterceptor implements HandlerInterceptor
{
    /**
     * 日志服务
     */
    private LogService logService;
    /**
     * 日志配置bean
     */
    private ConfigBean configBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {
        HttpSession session = request.getSession();

        Log log = new Log();
        Map<String, Object> extraValues = new HashMap<>();
        configBean.putParamMapping(extraValues, request, session);
        log.setExtraValues(extraValues);
        logService.addLog(log);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        // TODO
    }

    public LogService getLogService()
    {
        return logService;
    }

    public void setLogService(LogService logService)
    {
        this.logService = logService;
    }

    public ConfigBean getConfigBean()
    {
        return configBean;
    }

    public void setConfigBean(ConfigBean configBean)
    {
        this.configBean = configBean;
    }

}
