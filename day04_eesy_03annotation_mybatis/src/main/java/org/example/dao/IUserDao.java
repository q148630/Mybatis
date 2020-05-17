package org.example.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.domain.User;

import java.util.List;

/**
 * 在 Mybatis 中針對 CRUD 一共有四個註解
 * @Select @Inert @Update @Delete
 */
public interface IUserDao {

    /**
     * 方法: 查詢所有用戶
     * @return java.util.List<org.example.domain.User>
     */
    @Select("SELECT * FROM user")
    List<User> findAll();

    /**   
     * 方法: 保存用戶
     * @param user
     * @return void  
     */
    @Insert("INSERT INTO user(username, address, sex, birthday) VALUES(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    /**
     * 方法: 更新用戶
     * @param user
     */
    @Update("UPDATE user SET username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} WHERE id=#{id}")
    void updateUser(User user);

    /**
     * 方法: 刪除用戶
     * @param userId
     */
    @Delete("DELETE FROM user WHERE id=#{id}")
    void deleteUser(Integer userId);

    /**
     * 方法: 根據 id 查詢用戶
     * @param userId
     * @return org.example.domain.User
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(Integer userId);

    /**
     * 方法: 根據用戶名稱模糊查詢
     * @param username
     * @return java.util.List<org.example.domain.User>
     */
//    @Select("SELECT * FROM user WHERE username LIKE #{username}")
    @Select("SELECT * FROM user WHERE username LIKE '%${value}%'")
    List<User> findUserByName(String username);

    /**
     * 方法: 查詢總用戶數量
     * @return int
     */
    @Select("SELECT COUNT(*) FROM user")
    int findTotalUser();
}
