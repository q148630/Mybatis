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
import java.util.List;

/**
 * 測試 Mybatis 的 CRUD 操作
 */
public class UserTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before // 用於在測試方法執行之前執行
    public void init() throws IOException {
        // 1. 讀取配置文件，生成字節輸入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 取得 SqlSessionFactory 物件
        factory = new SqlSessionFactoryBuilder().build(in);
        // 3. 取得 SqlSession 物件
        sqlSession = factory.openSession(true);
        // 4. 取得 Dao 的代理物件
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After // 用於在測試方法執行之後執行
    public void destroy() throws IOException {
        // 提交事務
//        sqlSession.commit();
        // 6. 釋放資源
        sqlSession.close();
        in.close();
    }

    /**
     * 方法: 測試一級緩存
     */
    @Test
    public void testFirstLevelCache() {
        User user1 = userDao.findById(41);
        System.out.println(user1);

//        sqlSession.close();
//        // 再次取得 SqlSession 物件
//        sqlSession = factory.openSession();

        sqlSession.clearCache(); // 此方法也可以清空緩存

//        userDao = sqlSession.getMapper(IUserDao.class);

        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    /**
     * 方法: 測試一緩存的同步
     */
    @Test
    public void testClearCache() {
        // 1. 根據 id 查詢用戶
        User user1 = userDao.findById(41);
        System.out.println(user1);
        // 2. 更新用戶信息
        user1.setUsername("update user clear cache");
        user1.setAddress("XXXXXX");
        userDao.updateUser(user1);

        // 3. 再次查詢 id 為 41 的用戶
        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
}
