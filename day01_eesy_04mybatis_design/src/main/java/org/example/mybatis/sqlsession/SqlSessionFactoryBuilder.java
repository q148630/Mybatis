package org.example.mybatis.sqlsession;

import org.example.mybatis.cfg.Configuration;
import org.example.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import org.example.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用於建立一個 SqlSessionFactory 物件
 */  
public class SqlSessionFactoryBuilder {

    /**   
     * 方法: 根據參數的字節輸入流來建立一個 SqlSessionFactory 工廠
     * @param config
     * @return org.example.mybatis.sqlsession.SqlSessionFactory  
     */  
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }

}
