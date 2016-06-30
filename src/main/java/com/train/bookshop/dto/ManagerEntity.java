package com.train.bookshop.dto;

import java.util.Date;

/**
 * 后台管理用户
 * 
 * @Created by liuj-s on 2016-06-02.
 */
public class ManagerEntity {

    private String loginName;

    private String name;

    private String passwordDigest;

    private String passwordSalt;

    private Integer status;

    private Integer level;

    private Date createTime;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest == null ? null : passwordDigest.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ManagerEntity [loginName=" + loginName + ", name=" + name + ", passwordDigest=" + passwordDigest
               + ", passwordSalt=" + passwordSalt + ", status=" + status + ", level=" + level + ", createTime="
               + createTime + "]";
    }
}
