package com.train.bookshop.dto;

import java.util.Date;

/**
 * 返回的用户对象
 * 
 * @Created by liuj-s on 2016-06-03.
 */
public class LoginManager {

    private String  loginName;

    private String  name;

    private Integer status;

    private Integer level;

    private Date    createTime;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "User [loginName=" + loginName + ", name=" + name + ", status=" + status + ", level=" + level
               + ", createTime=" + createTime + "]";
    }
}
