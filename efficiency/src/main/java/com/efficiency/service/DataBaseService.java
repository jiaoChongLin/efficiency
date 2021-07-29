package com.efficiency.service;

import com.efficiency.entity.ColunmInfo;
import com.efficiency.entity.DataBaseInfo;
import com.efficiency.entity.TableInfo;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : Vincent.jiao
 * @Date : 2021/7/18 16:52
 * @Version : 1.0
 */
@Service
public class DataBaseService {
    public static DataBaseInfo dataBaseInfo = new DataBaseInfo();
    public static Connection connection;
    private Map<String, TableInfo> tablsMap = new ConcurrentHashMap<>();

    public void initConn(String username, String password, String url) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql://127.0.0.1:3306/wethink_sharding_dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true&autoReconnect=true";
//        String user = "root";
//        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet rs = databaseMetaData.getTables(null, null, "%", null);
        ResultSetMetaData metaData = rs.getMetaData();

        while(rs.next()){
            /**
             * TABLE_NAME : 表名
             * TABLE_TYPE : 类型 TABLE表   VIEW 视图
             * REMARKS : 注释
             * */

            TableInfo tableInfo = new TableInfo();
            tableInfo.setTable_name(rs.getString("TABLE_NAME"));
            tableInfo.setRemarks(rs.getString("REMARKS"));
            tableInfo.setTable_type(rs.getString("TABLE_TYPE"));



            tablsMap.put(tableInfo.getTable_name(), tableInfo);
        }

        rs.close();


        rs = databaseMetaData.getColumns(null, null, "%", null);
        metaData = rs.getMetaData();

        while(rs.next()){
            ColunmInfo colunmInfo = new ColunmInfo();
            colunmInfo.setRemarks(rs.getString("REMARKS"));
            colunmInfo.setType_name(rs.getString("TYPE_NAME"));
            colunmInfo.setColumn_name(rs.getString("COLUMN_NAME"));
            colunmInfo.setColumn_size(rs.getString("COLUMN_SIZE"));
            colunmInfo.setIs_nullable(rs.getString("IS_NULLABLE"));
            colunmInfo.setDecimal_digits(rs.getString("DECIMAL_DIGITS"));
            colunmInfo.setColumn_def(rs.getString("COLUMN_DEF"));
            colunmInfo.setIs_autoincrement(rs.getString("IS_AUTOINCREMENT"));

            TableInfo tableInfo = tablsMap.get(colunmInfo.getType_name());
            if (tableInfo == null) {
                continue;
            }

            if (tableInfo.getColunmInfos() == null) {
                tableInfo.setColunmInfos(new LinkedList<>());
            }

            tableInfo.getColunmInfos().add(colunmInfo);
        }

        rs.close();
    }

    public Map<String, TableInfo> getAllTable(String username, String password, String url){
        return getAllTable();
    }

    public Map<String, TableInfo> getAllTable(){
        return tablsMap;
    }

    public DataBaseInfo getDataBaseInfo() {
        return dataBaseInfo;
    }


}
