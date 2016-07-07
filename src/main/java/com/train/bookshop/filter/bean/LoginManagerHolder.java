package com.train.bookshop.filter.bean;

import com.train.bookshop.dto.LoginManager;

public class LoginManagerHolder {

    private static final ThreadLocal<LoginManager> holder = new ThreadLocal<LoginManager>();

    public static void clearLoginManager() {
        holder.remove();
    }

    public static LoginManager getLoginManager() {
        LoginManager ctx = holder.get();
        if (ctx == null) {
            ctx = createEmptyLoginManager();
            holder.set(ctx);
        }
        return ctx;
    }

    public static void setLoginManager(LoginManager loginManager) {
        holder.set(loginManager);
    }

    public static LoginManager createEmptyLoginManager() {
        return new LoginManager();
    }
}
