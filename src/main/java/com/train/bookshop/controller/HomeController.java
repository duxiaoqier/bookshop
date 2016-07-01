package com.train.bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页
 * 
 * @author dup, 2016-06-30
 */
@Controller
@RequestMapping("/")
public class HomeController extends AbstractController {

    @RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
    public ModelAndView index(ModelMap model) {
        return new ModelAndView("/shop/index");
    }

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    @ResponseBody
    public String go() {
        return "ok";
    }
}
