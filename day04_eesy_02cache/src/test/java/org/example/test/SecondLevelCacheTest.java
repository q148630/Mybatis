package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.IUserDao;
import org.example.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 測試 Mybatis 的 CRUD 操作
 */
public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;


    @Before // 用於在測試方法執行之前執行
    public void init() throws IOException {
        // 1. 讀取配置文件，生成字節輸入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 取得 SqlSessionFactory 物件
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After // 用於在測試方法執行之後執行
    public void destroy() throws IOException {
        in.close();
    }

    /**
     * 方法: 測試一級緩存
     */
    @Test
    public void testFirstLevelCache() {

        SqlSession sqlSession1 = factory.openSession(true);
        IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = userDao1.findById(41);
        System.out.println(user1);
        sqlSession1.close();

        SqlSession sqlSession2 = factory.openSession(true);
        IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = userDao2.findById(41);
        System.out.println(user2);
        sqlSession2.close();

        System.out.println(user1 == user2);
    }


}
