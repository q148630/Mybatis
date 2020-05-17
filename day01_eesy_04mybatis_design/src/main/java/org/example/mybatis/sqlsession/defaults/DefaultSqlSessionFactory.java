package org.example.mybatis.sqlsession.defaults;

import org.example.mybatis.cfg.Configuration;
import org.example.mybatis.sqlsession.SqlSession;
import org.example.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory 接口的實現類
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 方法: 用於建立一個新的操作資料庫物件
     * @return org.example.mybatis.sqlsession.SqlSession
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }


}
