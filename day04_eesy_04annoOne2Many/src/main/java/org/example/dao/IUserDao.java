package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.example.domain.User;

import java.util.List;

/**
 * 在 Mybatis 中針對 CRUD 一共有四個註解
 * @Select @Inert @Update @Delete
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 方法: 查詢所有用戶
     * @return java.util.List<org.example.domain.User>
     */
    @Select("SELECT * FROM user")
    @Results(id = "userMap",value = {
            @Result(id = true, property = "userId", column = "id"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userAddress", column = "address"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "org.example.dao.IAccountDao.findAccountByUid", fetchType= FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 方法: 根據 id 查詢用戶
     * @param userId
     * @return org.example.domain.User
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    @ResultMap("userMap")
    User findById(Integer userId);

    /**
     * 方法: 根據用戶名稱模糊查詢
     * @param username
     * @return java.util.List<org.example.domain.User>
     */
    @Select("SELECT * FROM user WHERE username LIKE #{username}")
    @ResultMap("userMap")
    List<User> findUserByName(String username);

}
