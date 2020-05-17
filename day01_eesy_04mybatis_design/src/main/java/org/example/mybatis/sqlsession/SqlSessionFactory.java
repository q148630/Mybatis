package org.example.mybatis.sqlsession;

public interface SqlSessionFactory {

    /**
     * 方法: 用於打開一個新的 SqlSession 物件
     * @return SqlSession
     */
    SqlSession openSession();

}
