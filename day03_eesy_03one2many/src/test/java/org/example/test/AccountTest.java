package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.IAccountDao;
import org.example.dao.IUserDao;
import org.example.domain.Account;
import org.example.domain.AccountUser;
import org.example.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 測試 Mybatis 的 CRUD 操作
 */
public class AccountTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before // 用於在測試方法執行之前執行
    public void init() throws IOException {
        // 1. 讀取配置文件，生成字節輸入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 取得 SqlSessionFactory 物件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3. 取得 SqlSession 物件
        sqlSession = factory.openSession(true);
        // 4. 取得 Dao 的代理物件
        accountDao = sqlSession.getMapper(IAccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for (Account account: accounts) {
            System.out.println("-------每個 account 的信息");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 方法: 測試查詢所有帳戶，同時包含用戶名稱和地址
     */
    @Test
    public void testFindAllAccountUser() {
        List<AccountUser> aus = accountDao.findAllAccount();
        for (AccountUser au: aus) {
            System.out.println(au);
        }
    }
}
