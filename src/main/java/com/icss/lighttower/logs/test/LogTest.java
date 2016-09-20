package com.icss.lighttower.logs.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;

import com.icss.lighttower.logs.entity.ConfigBean;
import com.icss.lighttower.logs.entity.Log;
import com.icss.lighttower.logs.service.LogService;
/**
 * 测试类
 * @author Administrator
 *
 */
public class LogTest
{
    /**
     * main方法
     * @param args main方法的参数 
     */
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:applicationContext-log.xml");
        Log log = new Log();
        ConfigBean configBean = (ConfigBean) applicationContext.getBean("configBean");
        LogService logService = (LogService) applicationContext.getBean("logService");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/v1/user");
        request.addHeader("ipAddress", "127.0.0.1");
        request.getSession().setAttribute("username", "zhangsan");
        Map<String, Object> extraValues = new HashMap<>();
        configBean.putParamMapping(extraValues, request, request.getSession());
        log.setExtraValues(extraValues);
        int status = logService.addLog(log);
        System.out.println(status);
        applicationContext.close();
    }
}
