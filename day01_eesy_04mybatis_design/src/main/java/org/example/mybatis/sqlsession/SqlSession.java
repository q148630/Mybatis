package org.example.mybatis.sqlsession;

/**
 * 自定義 Mybatis 中和資料庫進行操作的核心類
 * 它裡面可以建立 Dao 接口的代理物件
 */
public interface SqlSession {

    /**
     * 方法: 根據參數建立一個代理物件
     * @param daoInterfaceClass Dao接口的字節碼
     * @return T
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 方法: 釋放資源
     */
    void close();

}
