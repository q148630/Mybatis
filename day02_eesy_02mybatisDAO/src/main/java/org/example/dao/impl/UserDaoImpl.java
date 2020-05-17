package org.example.dao.impl;

import jdk.nashorn.internal.runtime.UserAccessorProperty;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.dao.IUserDao;
import org.example.domain.User;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        // 1. 根據 factory 取得 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 調用 SqlSession 中的方法，實現查詢列表
        List<User> users = session.selectList("org.example.dao.IUserDao.findAll"); // 參數就是能取得配置信息的 key
        // 3. 釋放資源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        // 1. 根據 factory 取得 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 調用方法實現保存
        session.insert("org.example.dao.IUserDao.saveUser", user);
        // 3. 提交事務
        session.commit();
        // 4. 釋放資源
        session.close();
    }

    public void updateUser(User user) {
        // 1. 根據 factory 取得 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 調用方法實現保存
        session.update("org.example.dao.IUserDao.updateUser", user);
        // 3. 提交事務
        session.commit();
        // 4. 釋放資源
        session.close();
    }

    public void deleteUser(Integer userId) {
        // 1. 根據 factory 取得 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 調用方法實現保存
        session.delete("org.example.dao.IUserDao.deleteUser", userId);
        // 3. 提交事務
        session.commit();
        // 4. 釋放資源
        session.close();
    }

    public User findById(Integer userId) {
        // 1. 根據 factory 取得 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 調用 SqlSession 中的方法，實現查詢一個
        User user = session.selectOne("org.example.dao.IUserDao.findById", userId); // 參數就是能取得配置信息的 key
        // 3. 釋放資源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        // 1. 根據 factory 取得 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 調用 SqlSession 中的方法，實現查詢列表
        List<User> users = session.selectList("org.example.dao.IUserDao.findByName", username); // 參數就是能取得配置信息的 key
        // 3. 釋放資源
        session.close();
        return users;
    }

    public int findTotal() {
        // 1. 根據 factory 取得 SqlSession 物件
        SqlSession session = factory.openSession();
        // 2. 調用 SqlSession 中的方法，實現查詢一個
        Integer count = session.selectOne("org.example.dao.IUserDao.findTotal"); // 參數就是能取得配置信息的 key
        // 3. 釋放資源
        session.close();
        return count;
    }
}
