package com.icss.lighttower.logs.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Log log = new Log(); Map<String, Object> extraValues = new HashMap<>();
 * configBean.putParamMapping(extraValues, request, session);
 * log.setExtraValues(extraValues); logService.addLog(log);
 * 
 * @author mengqingfeng01@chinasoftinc.com
 *
 */
public class ConfigBean
{
    /**
     * 前台参数与后台数据库映射的map
     */
    private Map<String, Object> fixParamMapping = new HashMap<>(0);
    /**
     * 请求里面的数据
     */
    private Map<String, Object> requestHeaderParamMapping = new HashMap<>(0);
    /**
     * session里面的数据
     */
    private Map<String, Object> sessionParamMapping = new HashMap<>(0);

    public Map<String, Object> getFixParamMapping()
    {
        return fixParamMapping;
    }

    public void setFixParamMapping(Map<String, Object> fixParamMapping)
    {
        this.fixParamMapping = fixParamMapping;
    }

    public Map<String, Object> getRequestHeaderParamMapping()
    {
        return requestHeaderParamMapping;
    }

    public void setRequestHeaderParamMapping(Map<String, Object> requestHeaderParamMapping)
    {
        this.requestHeaderParamMapping = requestHeaderParamMapping;
    }

    public Map<String, Object> getSessionParamMapping()
    {
        return sessionParamMapping;
    }

    public void setSessionParamMapping(Map<String, Object> sessionParamMapping)
    {
        this.sessionParamMapping = sessionParamMapping;
    }

    /**
     * 
     *
     * LogInterceptor.putParamMapping
     *
     * @Description: TODO(这里用一句话描述这个方法的作用)
     *
     * @param extraValues
     *            extraValues
     * @param request
     *            request
     * @param session
     *            HttpSession
     * 
     * @author sunyue02@chinasoftinc.com
     *
     * @date 2016年8月29日-下午2:50:30
     *
     */
    public void putParamMapping(Map<String, Object> extraValues, HttpServletRequest request, HttpSession session)
    {
        putFixParamMapping(extraValues, request);
        putRequestParamMapping(extraValues, request);
        putSessionParamMapping(extraValues, session);
    }

    /**
     * 放固定的值，访问日期，方法执行是否成功此种方式拿不到。
     * 
     * @param extraValues 固定参数配置 
     * @param request HttpServletRequest
     *            key=数据库字段，value=数据库值
     */
    private void putFixParamMapping(Map<String, Object> extraValues, HttpServletRequest request)
    {
        Set<Entry<String, Object>> set = fixParamMapping.entrySet();
        Iterator<Entry<String, Object>> it = set.iterator();
        while (it.hasNext())
        {
            Entry<String, Object> entry = it.next();
            if ("date".equals(entry.getKey()))
            {
                extraValues.put(String.valueOf(entry.getValue()), new Date());
            }
            else if ("result".equals(entry.getKey()))
            {
                extraValues.put(String.valueOf(entry.getValue()), 1);
            }

            else if ("url".equals(entry.getKey()))
            {
                extraValues.put(String.valueOf(entry.getValue()), request.getRequestURI());
            }
        }
    }

    /**
     * 放request里面的值
     * 
     * @param extraValues
     *            key=数据库字段，value=数据库值
     * @param request
     *            request
     */
    private void putRequestParamMapping(Map<String, Object> extraValues, HttpServletRequest request)
    {
        Set<Entry<String, Object>> set = requestHeaderParamMapping.entrySet();
        Iterator<Entry<String, Object>> it = set.iterator();
        while (it.hasNext())
        {
            Entry<String, Object> entry = it.next();
            extraValues.put(String.valueOf(entry.getValue()), request.getHeader(entry.getKey()));
        }
    }

    /**
     * 放session中的值
     * 
     * @param extraValues
     *            key=数据库字段，value=数据库值
     * @param session
     *            session
     */
    private void putSessionParamMapping(Map<String, Object> extraValues, HttpSession session)
    {
        Set<Entry<String, Object>> set = sessionParamMapping.entrySet();
        Iterator<Entry<String, Object>> it = set.iterator();
        while (it.hasNext())
        {
            Entry<String, Object> entry = it.next();
            extraValues.put(String.valueOf(entry.getValue()), session.getAttribute(entry.getKey()));
        }
    }
}
