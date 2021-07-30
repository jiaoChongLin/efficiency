package com.efficiency.service;

import cn.hutool.core.collection.CollectionUtil;
import com.efficiency.entity.CompleteTable;
import com.efficiency.entity.ConnInfo;
import com.efficiency.entity.DataBaseDalect;
import com.efficiency.entity.TableInfo;
import com.efficiency.generate.GenerateDMDialect;
import com.efficiency.generate.GenerateDialect;
import com.efficiency.generate.GenerateOracleDialect;
import com.efficiency.generate.GenerateSQLServerDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 数据库操作工具类.
 * @author vincent.jiao
 */
@Service
public class DataBaseToolService {
    @Autowired
    DataBaseMateDataService dataBaseMateDataService;

    public Map<String, Set<String>> getTableDifference (ConnInfo connInfo1, ConnInfo connInfo2) {
        Map<String, Set<String>> resultMap = new HashMap<>();

        Set<String> allTable1 = dataBaseMateDataService.getAllTableName(connInfo1);
        Set<String> allTable2 = dataBaseMateDataService.getAllTableName(connInfo2);

        Set<String> tmpSet = new HashSet<>(allTable1);
        tmpSet.removeAll(allTable2);
        resultMap.put(connInfo1.getConnIdentifier(), tmpSet);

        tmpSet = new HashSet<>(allTable2);
        tmpSet.removeAll(allTable1);
        resultMap.put(connInfo1.getConnIdentifier(), tmpSet);

        return resultMap;
    }

    public Map<String, List<CompleteTable>> translate (ConnInfo connInfo) {
        List<TableInfo> list = dataBaseMateDataService.getAllTableInfo(connInfo);
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }

        return translate(list);
    }

    public Map<String, List<CompleteTable>> translate (ConnInfo connInfo, List<String> tablelNameList) {
        List<TableInfo> list = dataBaseMateDataService.getAllTableInfo(connInfo);
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }

        List<TableInfo> tranList = new LinkedList<>();
        for (TableInfo item : list) {
            if (tablelNameList.contains(item.getTable_name())) {
                tranList.add(item);
            }
        }

        return translate(tranList);
    }

    public Map<String, List<CompleteTable>> translate (List<TableInfo> list) {
        Map<String, List<CompleteTable>> map = new HashMap<>();
        map.put(DataBaseDalect.SQLSERVER.value, new LinkedList<>());
        map.put(DataBaseDalect.ORACLE.value, new LinkedList<>());
        map.put(DataBaseDalect.DM.value, new LinkedList<>());

        for (TableInfo item : list) {
            GenerateDialect generateDialect = new GenerateSQLServerDialect();
            CompleteTable completeTable = generateDialect.generateCompleteTable(item);
            map.get(DataBaseDalect.SQLSERVER.value).add(completeTable);

            generateDialect = new GenerateOracleDialect();
            completeTable = generateDialect.generateCompleteTable(item);
            map.get(DataBaseDalect.ORACLE.value).add(completeTable);

            generateDialect = new GenerateDMDialect();
            completeTable = generateDialect.generateCompleteTable(item);
            map.get(DataBaseDalect.DM.value).add(completeTable);
        }

        return map;
    }
}
