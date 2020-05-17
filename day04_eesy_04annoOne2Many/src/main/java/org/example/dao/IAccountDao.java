package org.example.dao;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.example.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 方法: 查詢所有帳戶，並且取得每個帳戶所屬的用戶訊息
     * @return java.util.List<org.example.domain.Account>
     */
    @Select("SELECT * FROM account")
    @Results(id = "accountMap",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user", column = "uid", one = @One(select="org.example.dao.IUserDao.findById", fetchType= FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 方法: 根據用戶 id 查詢帳戶訊息
     * @param userId
     * @return java.util.List<org.example.domain.Account>
     */
    @Select("SELECT * FROM account WHERE uid=#{userId}")
    List<Account> findAccountByUid(Integer userId);
}
