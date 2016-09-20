package com.icss.lighttower.logs.entity;

import java.util.Map;

/**
 * 日志实体类，入库参数及值都放入到map中，key=表的字段名
 * 
 * @author Administrator
 *
 */
public class Log
{
    /**
     * 日志编号
     */
    private Integer id;
    /**
     * 业务主键
     */
    private String key;
    /**
     * 其它值
     */
    private Map<String, Object> extraValues;

    public Map<String, Object> getExtraValues()
    {
        return extraValues;
    }

    /**
     * 
     * @param extraValues
     *            key=表的字段名
     */
    public void setExtraValues(Map<String, Object> extraValues)
    {
        this.extraValues = extraValues;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
