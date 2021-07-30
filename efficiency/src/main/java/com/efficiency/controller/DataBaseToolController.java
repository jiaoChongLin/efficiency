package com.efficiency.controller;

import com.efficiency.entity.ConnInfo;
import com.efficiency.service.DataBaseMateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vincent.jiao
 */
@RestController
@RequestMapping("/db")
public class DataBaseToolController {
    @Autowired
    DataBaseMateDataService dataBaseMateDataService;

    /**
     * 返回所有表名
     * @param connInfo
     * @return
     */
    @RequestMapping("getAllTable")
    public Object getAllTable(ConnInfo connInfo) {
        return null;
    }

    /**
     * 初始化链接
     * @param connInfo
     * @return
     */
    @RequestMapping("initConnInfo")
    public Object initConnInfo(ConnInfo connInfo) {
        return null;
    }

    /**
     * 返回所有链接
     * @param connInfo
     * @return
     */
    @RequestMapping("getAllConnInfo")
    public Object getAllConnInfo(ConnInfo connInfo) {
        return null;
    }

    /**
     * 刷新链接
     * @param connInfo
     * @return
     */
    @RequestMapping("refreshConnInfo")
    public Object refreshConnInfo(ConnInfo connInfo) {
        return null;
    }

    /**
     * 翻译sql
     * @param connInfo
     * @return
     */
    @RequestMapping("translateSql")
    public Object translateSql(ConnInfo connInfo) {
        return null;
    }
}
