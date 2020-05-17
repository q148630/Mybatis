package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.IUserDao;
import org.example.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis 的入門範例
 */  
public class MybatisTest {

    /**   
     * 方法: 入門範例
     * @param args
     */  
    public static void main(String[] args) throws IOException {
        // 1. 讀取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 建立 SqlSessionFactory 工廠
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3. 使用工廠生產 SqlSession 物件
        SqlSession session = factory.openSession();
        // 4. 使用 SqlSession 建立 Dao 接口的代理物件
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 5. 使用代理物件執行方法
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }
        // 6. 釋放資源
        session.close();
        in.close();
    }
    
}
