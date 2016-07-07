package com.train.bookshop.dto;

import java.io.Serializable;

/**
 * 保存在会话中的对象（已经登录的用户）
 * 
 * @author singo
 */
public class SigninUser implements Serializable {

    private static final long serialVersionUID = 5562136997706961025L;

    private Long              id;
    private String            nickname;

    private String            email;
    private String            mobile;

    public SigninUser() {
    }

    public SigninUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "SigninUser [id=" + id + ", nickname=" + nickname + ", email=" + email + ", mobile=" + mobile + "]";
    }

}
