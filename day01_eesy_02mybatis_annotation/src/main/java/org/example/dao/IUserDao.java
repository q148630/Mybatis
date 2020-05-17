package org.example.dao;

import org.apache.ibatis.annotations.Select;
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
    @Select("SELECT * FROM user")
    List<User> findAll();
}
