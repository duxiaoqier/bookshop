package com.train.bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页
 * 
 * @author dup, 2016-06-30
 */
@Controller
@RequestMapping("/")
public class HomeController extends AbstractController {

    @RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "redirect:/shop/book";
    }

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    @ResponseBody
    public String go() {
        return "ok";
    }
}
