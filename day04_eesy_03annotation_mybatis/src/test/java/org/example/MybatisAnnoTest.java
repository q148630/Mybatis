package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.IUserDao;
import org.example.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisAnnoTest {

    /**   
     * 方法: 測試基於註解的 Mybatis 使用
     */  
    public static void main(String[] args) throws IOException {
        // 1. 取得字節輸入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 根據字節輸入流建立 SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3. 根據 SqlSessionFactory 生產一個 SqlSession
        SqlSession session = factory.openSession();
        // 4. 使用 SqlSession 取得 Dao 的代理物件
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 5. 執行 Dao 的方法
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }
        // 6. 釋放資源
        session.close();
        in.close();
    }
}
