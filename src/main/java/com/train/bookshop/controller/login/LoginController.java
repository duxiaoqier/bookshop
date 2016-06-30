package com.train.bookshop.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.train.bookshop.dto.GeneralResponse;
import com.train.bookshop.dto.LoginManager;
import com.train.bookshop.dto.ManagerEntity;
import com.train.bookshop.service.ManagerService;

@Controller
@RequestMapping(value = { "/" })
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String LOGIN_SEESION_USER_INFO = "l_u_i";

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/j_verify_login", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResponse<LoginManager> login(@RequestParam("loginName") String loginName,
                                               @RequestParam("password") String password, HttpServletRequest request,
                                               HttpServletResponse response) {

        logger.debug("post login request body:" + loginName);
        ManagerEntity managerEntity = managerService.getManagerByLoginName(loginName);

        // 验证密码是否正确(password进行加密)
        managerService.verifyPassword(managerEntity, password);

        LoginManager loginManager = new LoginManager();
        BeanUtils.copyProperties(managerEntity, loginManager);

        // 密码验证成功后，把用户(user)信息存入session
        request.getSession().setAttribute(LOGIN_SEESION_USER_INFO, loginManager);
        response.addHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + "; Path=/; HttpOnly");
        return new GeneralResponse<LoginManager>(loginManager);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("is logout");

        // 在没有session的情况下应直接属于非登录状态
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 有session时，直接删除登录时保存在session里的用户对象
            session.removeAttribute(LOGIN_SEESION_USER_INFO);
        }
        return new ModelAndView(new RedirectView("login"));
    }
}
