package org.example.dao;

import org.example.domain.User;

import java.util.List;

/**
 * 用戶的持久層接口
 */
public interface IUserDao {

    /**
     * 方法: 查詢所有用戶
     * @return java.util.List<org.example.domain.User>
     */
    List<User> findAll();

    /**
     * 方法: 保存用戶
     * @param user
     */
    void saveUser(User user);

    /**
     * 方法: 更新用戶
     * @param user
     */
    void updateUser(User user);

    /**
     * 方法: 根據 Id 刪除用戶
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 方法: 根據 Id 查詢用戶信息
     * @param userId
     * @return org.example.domain.User
     */
    User findById(Integer userId);

    /**
     * 方法: 根據名稱模糊查詢用戶信息
     * @param username
     * @return java.util.List<org.example.domain.User>
     */
    List<User> findByName(String username);

    /**
     * 方法: 查詢總用戶數
     * @return int
     */
    int findTotal();

}
