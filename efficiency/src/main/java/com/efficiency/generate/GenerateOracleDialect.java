package com.efficiency.generate;

import com.efficiency.entity.TableInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Vincent.jiao
 * @Date : 2021/7/18 20:23
 * @Version : 1.0
 */
public class GenerateOracleDialect extends AbstractGenerateDialect{
    Map<String, String> typeMapper = new HashMap<>();

    public GenerateOracleDialect () {
        typeMapper.put("TINYINT", "");
        typeMapper.put("SMALLINT", "");
        typeMapper.put("MEDIUMINT", "");
        typeMapper.put("INT", "");
        typeMapper.put("INTEGER", "");
        typeMapper.put("BIGINT", "");

        typeMapper.put("FLOAT", "");
        typeMapper.put("DOUBLE", "");
        typeMapper.put("DECIMAL", "");

        typeMapper.put("DATE", "");
        typeMapper.put("TIME", "");
        typeMapper.put("YEAR", "");
        typeMapper.put("DATETIME", "");
        typeMapper.put("TIMESTAMP", "");

        typeMapper.put("CHAR", "");
        typeMapper.put("VARCHAR", "");
        typeMapper.put("TINYBLOB", "");
        typeMapper.put("TINYTEXT", "");
        typeMapper.put("BLOB", "");
        typeMapper.put("TEXT", "");
        typeMapper.put("MEDIUMBLOB", "");
        typeMapper.put("MEDIUMTEXT", "");
        typeMapper.put("LONGBLOB", "");
        typeMapper.put("LONGTEXT", "");
    }

    @Override
    public String getType(String type, String size) {
        return null;
    }

    @Override
    public List<String> generateCommon(TableInfo tableInfo) {
        return null;
    }
}
