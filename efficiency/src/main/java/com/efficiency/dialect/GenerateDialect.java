package com.efficiency.dialect;

import com.efficiency.entity.TableInfo;

import java.util.List;

/**
 * @Author : Vincent.jiao
 * @Date : 2021/7/18 20:21
 * @Version : 1.0
 */
public interface GenerateDialect {
    public List<String> getCreateTableSql(TableInfo tableInfo);
}
