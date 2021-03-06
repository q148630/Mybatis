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
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before // 用於在測試方法執行之前執行
    public void init() throws IOException {
        // 1. 讀取配置文件，生成字節輸入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 取得 SqlSessionFactory 物件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
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
     * 方法: 測試查詢所有
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println("--------每個用戶的信息-----------");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

}
