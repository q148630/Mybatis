package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.IUserDao;
import org.example.domain.QueryVo;
import org.example.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 測試 Mybatis 的 CRUD 操作
 */
public class MybatisTest {

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
        // 5. 執行查詢所有方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 方法: 測試查詢一個
     */
    @Test
    public void testFindOne() {
        // 5. 執行查詢一個方法
        User user = userDao.findById(50);
        System.out.println(user);
    }

    /**
     * 方法: 測試模糊查詢操作
     */
    @Test
    public void testFindByName() {
        // 5. 執行模糊查詢方法
        List<User> users = userDao.findByName("%王%");
//        List<User> users = userDao.findByName("王");
        for (User user: users) {
            System.out.println(user);
        }
    }

    /**
     * 方法: 測試使用 QueryVo 作用查詢條件
     */
    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        // 5. 執行模糊查詢方法
        List<User> users = userDao.findUserByVo(vo);
        for (User u: users) {
            System.out.println(u);
        }
    }

    /**
     * 方法: 測試查詢條件 if 標籤的使用
     */
    @Test
    public void testFindByCondition() {
        User u = new User();
        u.setUserName("老王");
//        u.setUserSex("女");
        // 5. 執行查詢所有方法
        List<User> users = userDao.findUserByCondition(u);
        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 方法: 測試 foreach 標籤的使用
     */
    @Test
    public void testFindInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(46);
        vo.setIds(list);
        // 5. 執行查詢所有方法
        List<User> users = userDao.findUserInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }

    }
}
