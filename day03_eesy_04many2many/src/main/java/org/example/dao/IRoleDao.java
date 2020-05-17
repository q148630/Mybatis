package org.example.dao;

import org.example.domain.Role;

import java.util.List;

public interface IRoleDao {

    /**
     * 方法: 查詢所有角色
     * @return java.util.List<org.example.domain.Role>
     */
    List<Role> findAll();

}
