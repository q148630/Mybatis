package org.example.dao;

import org.example.domain.QueryVo;
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
     * 方法: 根據 QueryVo 中的條件查詢用戶
     @param vo
     * @return java.util.List<org.example.domain.User>
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 方法: 根據傳入的參數條件
     * @param user 查詢的條件。有可能有用戶名，有可能有性別，也有可能有地址，還有可能是都有
     * @return java.util.List<org.example.domain.User>
     */
    List<User> findUserByCondition(User user);

    /**
     * 方法: 根據 QueryVo 中提供的 id 集合，查詢用戶信息
     * @param vo
     * @return java.util.List<org.example.domain.User>
     */
    List<User> findUserInIds(QueryVo vo);
}
