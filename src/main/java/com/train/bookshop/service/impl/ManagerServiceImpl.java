package com.train.bookshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.train.bookshop.dao.mapper.ManagerEntityMapper;
import com.train.bookshop.dto.ManagerEntity;
import com.train.bookshop.exception.LoginException;
import com.train.bookshop.service.ManagerService;
import com.train.bookshop.util.PasswordUtils;

/**
 * 管理者服务
 * 
 * @Created by liuj-s on 2016-06-02.
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerEntityMapper managerEntityMapper;

    @Override
    public ManagerEntity getManagerByLoginName(String loginName) {

        Assert.notNull(loginName, "LoginName must not be null.");

        List<ManagerEntity> managerEntities = managerEntityMapper.selectByLoginName(loginName);
        if (CollectionUtils.isEmpty(managerEntities)) {
            throw new LoginException("LoginName does not exist ...");
        }
        return managerEntities.get(0);
    }

    @Override
    public void verifyPassword(ManagerEntity managerEntity, String password) {

        // 密码不能为null，且大于或等于6
        // StringUtil.checkLength(password, "Wrong username or password", 6);

        // 加盐后的密码算法 与 数据库密码校验
        if (!PasswordUtils.isLegacyPasswordValid(managerEntity.getPasswordDigest(), password,
                                                 managerEntity.getPasswordSalt())) {
            throw new LoginException("Wrong password ...");
        }
    }

    @Override
    public void saveManager(String loginName, String name, String password) {

        Assert.notNull(loginName, "LoginName must not be null.");
        Assert.notNull(name, "name must not be null.");
        Assert.notNull(password, "password must not be null.");

        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setLoginName(loginName);
        managerEntity.setName(name);

        beforeCreateUser(managerEntity, password);
        managerEntityMapper.insert(managerEntity);
    }

    /**
     * 创建用户前
     * 
     * @param adminUserEntity
     */
    private void beforeCreateUser(ManagerEntity managerEntity, String password) {

        // 密码计算
        calculatePassword(managerEntity, password);

        managerEntity.setLevel(1);
        managerEntity.setStatus(1);
        managerEntity.setCreateTime(new Date());
    }

    /**
     * 密码计算
     * 
     * @param adminUser
     * @param password
     */
    private void calculatePassword(ManagerEntity managerEntity, String password) {
        // 密码计算
        // 1.获取密码盐
        String passwordSalt = PasswordUtils.generateSalt();
        managerEntity.setPasswordSalt(passwordSalt);
        // 2.使用密码盐对密码加密
        managerEntity.setPasswordDigest(PasswordUtils.encodeLegacyPassword(password, passwordSalt));
    }
}
