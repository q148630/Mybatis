package org.example.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.dao.IUserDao;
import org.example.domain.User;

import java.util.List;

/**
 *
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        // 1. 使用工廠建立 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 使用 session 執行查詢所有方法
        List<User> users = session.selectList("org.example.dao.IUserDao.findAll");
        session.close();
        // 3. 返回查詢結果
        return users;
    }

}
