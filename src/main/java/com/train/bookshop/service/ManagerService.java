/**
 * 
 */
package com.train.bookshop.service;

import com.train.bookshop.dto.ManagerEntity;

/**
 * 管理者接口
 * 
 * @Created by liuj-s on 2016-06-02.
 */
public interface ManagerService {

    /**
     * 根据登录名获取对象
     * 
     * @param loginName
     * @return
     */
    ManagerEntity getManagerByLoginName(String loginName);

    /**
     * 校验密码是否正确
     * 
     * @param mobile
     * @param password
     * @return
     */
    void verifyPassword(ManagerEntity managerEntity, String password);

    /**
     * 新增账号
     * 
     * @param loginName
     * @param name
     * @param password
     */
    void saveManager(String loginName, String name, String password);
}
