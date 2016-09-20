package com.icss.lighttower.logs.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.icss.lighttower.logs.entity.Log;

/**
 * &lt;pre&gt; 日志Dao类，用法，注入logSql属性，用户向数据库录入记录 &lt;/pre&gt;
 * 
 * @author Administrator
 *
 */
public class LogDao
{
    /**
     * 执行日志的sql
     */
    private String tableName = "";
    /**
     * 录入执行对象
     */
    private SimpleJdbcInsert insert;

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public LogDao(DataSource dataSource, String tableName)
    {
        insert = new SimpleJdbcInsert(dataSource).withTableName(tableName).usingGeneratedKeyColumns("id");
    }

    public LogDao(JdbcTemplate jdbcTemplate, String tableName)
    {
        insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(tableName).usingGeneratedKeyColumns("id");
    }

    /**
     * 添加日志
     * 
     * @param log
     *            要添加的日志entity
     * @return 0 成功，其它失败。
     */
    public int addLog(Log log)
    {
        int status = 0;
        Map<String, Object> parameters = new HashMap<String, Object>(0);
        parameters.putAll(log.getExtraValues());
        status = insert.execute(parameters);
        return status;
    }
}
