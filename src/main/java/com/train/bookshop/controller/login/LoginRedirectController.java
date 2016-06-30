package com.train.bookshop.controller.login;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.train.bookshop.dto.LoginManager;
import com.train.bookshop.util.DesUtil;

/**
 * 首页
 * 
 * @author singo, 2016-02-20
 */
@Controller
public class LoginRedirectController {

    public static final String LOGIN_SEESION_USER_INFO = "l_u_i";

    @Value("${app.path}")
    private String appPath;

    @Value("${bfjob.path}")
    private String jobPath;

    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public ModelAndView toJob(HttpServletRequest request) {
        LoginManager loginManager = (LoginManager) request.getSession().getAttribute(LOGIN_SEESION_USER_INFO);
        if (loginManager == null) {
            return new ModelAndView(new RedirectView(appPath + "/login"));
        }
        String sk = generateSk(loginManager);
        return new ModelAndView(new RedirectView(jobPath + "/login?sk=" + sk));
    }

    private String generateSk(LoginManager loginManager) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 5);
        String data = loginManager.getLoginName() + ";" + c.getTimeInMillis();
        return DesUtil.encrypt(data);
    }
}
