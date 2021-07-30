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
public class GenerateDMDialect extends AbstractGenerateDialect {
    Map<String, String> typeMapper = new HashMap<>();

    public GenerateDMDialect () {
        typeMapper.put("TINYINT", "TINYINT");
        typeMapper.put("SMALLINT", "SMALLINT");
        typeMapper.put("MEDIUMINT", "INT");
        typeMapper.put("INT", "INT");
        typeMapper.put("INTEGER", "INTEGER");
        typeMapper.put("BIGINT", "BIGINT");

        typeMapper.put("FLOAT", "FLOAT");
        typeMapper.put("DOUBLE", "DOUBLE");
        typeMapper.put("DECIMAL", "DECIMAL");

        typeMapper.put("DATE", "DATE");
        typeMapper.put("TIME", "TIME");
        typeMapper.put("YEAR", ""); // 这个不知道
        typeMapper.put("DATETIME", "DATETIME");
        typeMapper.put("TIMESTAMP", "TIMESTAMP");

        typeMapper.put("CHAR", "CHAR");
        typeMapper.put("VARCHAR", "VARCHAR");
        typeMapper.put("TINYBLOB", "BLOB");
        typeMapper.put("TINYTEXT", "TEXT");
        typeMapper.put("BLOB", "BLOB");
        typeMapper.put("TEXT", "TEXT");
        typeMapper.put("MEDIUMBLOB", "BLOB");
        typeMapper.put("MEDIUMTEXT", "TEXT");
        typeMapper.put("LONGBLOB", "BLOB");
        typeMapper.put("LONGTEXT", "TEXT");
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
