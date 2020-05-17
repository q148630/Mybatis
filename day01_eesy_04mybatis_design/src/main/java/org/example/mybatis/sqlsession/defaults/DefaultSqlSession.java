package org.example.mybatis.sqlsession.defaults;

import org.example.mybatis.cfg.Configuration;
import org.example.mybatis.sqlsession.SqlSession;
import org.example.mybatis.sqlsession.proxy.MapperProxy;
import org.example.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession 接口的實現類
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 方法: 用於建立代理物件
     * @param daoInterfaceClass Dao接口的字節碼
     * @return T
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[] {daoInterfaceClass}, new MapperProxy(cfg.getMappers(), connection));
    }

    /**
     * 方法: 用於釋放資源
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
