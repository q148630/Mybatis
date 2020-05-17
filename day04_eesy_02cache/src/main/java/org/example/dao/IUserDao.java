package org.example.dao;

import org.example.domain.User;

import java.util.List;

/**
 * 用戶的持久層接口
 */
public interface IUserDao {

    /**
     * 方法: 查詢所有用戶，同時取得用戶下所有帳戶的信息
     * @return java.util.List<org.example.domain.User>
     */
    List<User> findAll();

    /**
     * 方法: 根據 Id 查詢用戶信息
     * @param userId
     * @return org.example.domain.User
     */
    User findById(Integer userId);

    /**
     * 方法: 更新用戶信息
     * @param user
     */
    void updateUser(User user);
}
