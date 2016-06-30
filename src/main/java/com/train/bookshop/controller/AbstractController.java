package com.train.bookshop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.train.bookshop.util.RelativeDateFormat;

import freemarker.ext.beans.BeansWrapper;

/**
 * Controller抽象类，抽取了公共方法
 * 
 * @author singo, 2016-02-20
 */
public class AbstractController {

    private static final Map<String, Object> CLASS_MAP = new HashMap<String, Object>();

    protected static final String LOGIN_SEESION_USER_INFO = "j_l_u_i";

    static {
        CLASS_MAP.put("relativeDateFormat", new RelativeDateFormat());
    }

    /**
     * 获得PageNo
     * 
     * @param pageNoStr
     * @return
     */
    protected Integer getPageNo(String pageNoStr) {
        if (pageNoStr == null || pageNoStr.length() <= 0) {
            return 1;
        }
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException e) {
            pageNo = 1;
        }
        return pageNo;
    }

    /**
     * 在Freemarker模板中显示枚举
     * 
     * @param model
     */
    protected void supportEnum(ModelMap model) {
        model.addAttribute("enums", BeansWrapper.getDefaultInstance().getEnumModels());
    }

    /**
     * 在Freemarker模板中支持java静态方法
     * 
     * @param model
     */
    protected void supportJavaMethod(ModelMap model) {
        model.addAttribute("classMap", CLASS_MAP);
    }
}
