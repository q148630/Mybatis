package org.example.dao;

import org.example.domain.Account;
import org.example.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    /**
     * 方法: 查詢所有帳戶，同時要取得當前帳戶的所屬用戶信息
     * @return java.util.List<org.example.domain.Account>
     */
    List<Account> findAll();

    /**
     * 方法: 查詢所有帳戶，並且帶有用戶名稱和地址信息
     * @return java.util.List
     */
    List<AccountUser> findAllAccount();

}
