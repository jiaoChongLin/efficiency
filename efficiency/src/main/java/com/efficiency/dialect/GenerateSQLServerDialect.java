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
public class GenerateSQLServerDialect implements GenerateDialect {
    @Override
    public List<String> getCreateTableSql(TableInfo tableInfo) {
        if (!tableInfo.getTable_type().equals("TABLE")) {
            return null;
        }

        Map<String, String> params = new HashMap<>();
        String createTable = " create table {table_name}  ( ";
        params.put("{table_name}", tableInfo.getTable_name());

        if (CollectionUtil.isEmpty(tableInfo.getColunmInfos())) {
            return null;
        }

        for (int i = 0, size = tableInfo.getColunmInfos().size(); i < size; i++) {
            ColunmInfo info = tableInfo.getColunmInfos().get(i);
            String colInfo = "";
            String tmp = "colName_" + i;
            params.put(tmp, info.getColumn_name());
            colInfo += "{"+tmp+"} ";

            tmp = "colType_" + i;
            params.put(tmp, info.getType_name());
            colInfo += "{"+tmp+"} ";

            if (StrUtil.isNotEmpty(info.getColumn_size())) {
                tmp = "colSize_" + i;
                params.put(tmp, info.getColumn_size());
                colInfo += "{"+tmp+"} ";
            }

            if (StrUtil.isNotEmpty(info.getIs_autoincrement()) && "YES".equalsIgnoreCase(info.getIs_autoincrement())) {
                tmp = "colIsAuto_" + i;
                params.put(tmp, " identity ");
                colInfo += "{"+tmp+"} ";
            }

            if (StrUtil.isNotEmpty(info.getColumn_def())) {
                tmp = "colDefault_" + i;
                params.put(tmp, " default '" + (info.getColumn_def()) + "' ");
                colInfo += "{"+tmp+"} ";
            }

            if (StrUtil.isNotEmpty(info.getIs_nullable()) && "NO".equalsIgnoreCase(info.getIs_nullable())) {
                tmp = "colNotNull_" + i;
                params.put(tmp, " not null ");
                colInfo += "{"+tmp+"} ";
            }

            colInfo += i+1 == size ? "" : ",";
            createTable += colInfo;
        }

        createTable += " ) ";
        List<String> list = new LinkedList<>();
        list.add(StrUtil.format(createTable, params));

        //增加注释

        return list;
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        DataBaseService dataBaseService = new DataBaseService();
        dataBaseService.initConn("root", "123456", "jdbc:mysql://127.0.0.1:3306/wethink_sharding_dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true&autoReconnect=true");

        Map<String, TableInfo> map = dataBaseService.getAllTable();
        GenerateSQLServerDialect sqlServerDialect = new GenerateSQLServerDialect();
        for (String tableName : map.keySet()) {
            System.out.println(sqlServerDialect.getCreateTableSql(map.get(tableName)));
        }
    }
}
