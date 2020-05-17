package org.example.dao;

import org.example.domain.User;

import java.util.List;

/**
 * 用戶的持久層接口
 */
public interface IUserDao {

    /**
     * 方法: 查詢所有操作
     * @return User
     */
    List<User> findAll();
}
