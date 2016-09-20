package com.icss.lighttower.logs.service;

import com.icss.lighttower.logs.dao.LogDao;
import com.icss.lighttower.logs.entity.Log;

/**
 * 添加日志的service类
 * 
 * @author Administrator
 *
 */
public class LogService
{
    /**
     * 日志Dao
     */
    private LogDao dao;

    public LogService(LogDao dao)
    {
        this.dao = dao;
    }

    public LogService()
    {

    }

    public LogDao getDao()
    {
        return dao;
    }

    public void setDao(LogDao dao)
    {
        this.dao = dao;
    }

    /**
     * 添加日志
     * 
     * @param log 要添加的日志对象
     * @return 返回dao的执行结果
     */
    public int addLog(Log log)
    {
        if (null == dao || null == log)
        {
            return -1;
        }
        return dao.addLog(log);
    }
}
