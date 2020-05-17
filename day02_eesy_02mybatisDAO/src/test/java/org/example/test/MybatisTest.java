package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.IUserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 測試 Mybatis 的 CRUD 操作
 */
public class MybatisTest {

    private InputStream in;
    private IUserDao userDao;

    @Before // 用於在測試方法執行之前執行
    public void init() throws IOException {
        // 1. 讀取配置文件，生成字節輸入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 取得 SqlSessionFactory 物件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3. 使用工廠物件，建立 Dao 物件
        userDao = new UserDaoImpl(factory);
    }

    @After // 用於在測試方法執行之後執行
    public void destroy() throws IOException {
        // 4. 釋放資源
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
     * 方法: 測試保存操作
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Dao impl User");
        user.setAddress("000");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前: " + user);
        // 5. 執行保存方法
        userDao.saveUser(user);
        System.out.println("保存操作之後: " + user);
    }

    /**
     * 方法: 測試更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(50);
        user.setUsername("Dao impl updateuser");
        user.setAddress("000");
        user.setSex("女");
        user.setBirthday(new Date());

        // 5. 執行更新方法
        userDao.updateUser(user);

    }

    /**
     * 方法: 測試刪除操作
     */
    @Test
    public void testDelete() {
        // 5. 執行刪除方法
        userDao.deleteUser(54);
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
     * 方法: 測試查詢總記錄條數
     */
    @Test
    public void testFindTotal() {
        // 5. 執行查詢總記錄條數
        int count = userDao.findTotal();
        System.out.println(count);
    }

}
