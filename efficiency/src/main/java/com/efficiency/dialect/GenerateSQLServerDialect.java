package com.efficiency.dialect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.efficiency.entity.ColunmInfo;
import com.efficiency.entity.TableInfo;
import com.efficiency.service.DataBaseService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author : Vincent.jiao
 * @Date : 2021/7/18 20:21
 * @Version : 1.0
 */
public class GenerateSQLServerDialect extends AbstractGenerateDialect {
    Map<String, String> typeMapper = new HashMap<>();

    public GenerateSQLServerDialect () {
        typeMapper.put("", "");
        typeMapper.put("", "");
        typeMapper.put("", "");
        typeMapper.put("", "");
        typeMapper.put("", "");
        typeMapper.put("", "");
        typeMapper.put("", "");
    }

    @Override
    public String getCreateTableSql(TableInfo tableInfo) {
        //增加注释

        return null;
    }

    @Override
    public String getType(String type, String size) {
        return null;
    }

    @Override
    public List<String> generateCommon(TableInfo tableInfo) {
        return null;
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        DataBaseService dataBaseService = new DataBaseService();
        dataBaseService.initConn("root", "123456", "jdbc:mysql://127.0.0.1:3306/wethink_sharding_dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true&autoReconnect=true");

        Map<String, TableInfo> map = dataBaseService.getAllTable();
        GenerateSQLServerDialect sqlServerDialect = new GenerateSQLServerDialect();
//        for (String tableName : map.keySet()) {
//            System.out.println(sqlServerDialect.getCreateTableSql(map.get(tableName)));
//        }
    }
}
