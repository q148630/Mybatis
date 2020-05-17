package org.example.mybatis.sqlsession.proxy;

import org.example.mybatis.cfg.Mapper;
import org.example.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 *
 */
public class MapperProxy implements InvocationHandler {

    // map 的 key 是全限定類名 + 方法名
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 方法: 用於對方法進行增強的，我們的增強其實就是調用 selectList 方法
     * @param proxy
     * @param method
     * @param args
     * @return java.lang.Object
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1. 取得方法名
        String methodName = method.getName();
        // 2. 取得方法所在類的名稱
        String className = method.getDeclaringClass().getName();
        // 3. 組合 key
        String key = className + "." + methodName;
        // 4. 取得 mappers 中的 Mapper 物件
        Mapper mapper = mappers.get(key);
        // 5. 判斷是否有 mapper
        if (mapper == null) {
            throw new IllegalArgumentException("傳入的參數有問題");
        }
        // 6. 調用工具類執行查詢所有
        return new Executor().selectList(mapper, conn);
    }


}
