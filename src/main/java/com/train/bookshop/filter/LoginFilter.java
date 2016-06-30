package com.train.bookshop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import com.train.bookshop.dto.LoginManager;

@Service("loginFilter")
public class LoginFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Value("${app.path}")
    private String appPath;

    public static final String LOGIN_SEESION_USER_INFO = "l_u_i";
    public static final String LOGIN_BEFORE_URL        = "login_before_url";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                              ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        HttpSession session = httpReq.getSession();
        LoginManager loginManager = (LoginManager) session.getAttribute(LOGIN_SEESION_USER_INFO);
        if (loginManager != null) {
            logger.debug("doFilter... is login:" + loginManager);
            chain.doFilter(request, response);
            return;
        }
        httpResp.sendRedirect(appPath + "/login");
    }
}
