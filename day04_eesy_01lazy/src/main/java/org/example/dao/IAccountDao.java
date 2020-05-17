package org.example.dao;

import org.example.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 方法: 查詢所有帳戶，同時要取得當前帳戶的所屬用戶信息
     * @return java.util.List<org.example.domain.Account>
     */
    List<Account> findAll();

    /**
     * 方法: 根據用戶 id 查詢帳戶信息
     * @param uid
     * @return java.util.List<org.example.domain.Account>
     */
    List<Account> findAccountByUid(Integer uid);

}
