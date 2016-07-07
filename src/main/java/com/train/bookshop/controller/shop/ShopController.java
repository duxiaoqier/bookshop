package com.train.bookshop.controller.shop;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.bean.page.PagedList;
import com.train.bookshop.controller.AbstractController;
import com.train.bookshop.dto.Book;
import com.train.bookshop.dto.ShopCartDetails;
import com.train.bookshop.enums.BookType;
import com.train.bookshop.service.BookService;
import com.train.bookshop.service.ShopService;

/**
 * 查询任务执行信息
 * 
 * @author dup, 2016-03-04
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends AbstractController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String queryBook(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "type", required = false) Byte type,
                            @RequestParam(value = "page", required = false) String pageNoStr, ModelMap model) {

        supportEnum(model);
        supportJavaMethod(model);

        // PAGE
        Integer pageNo = getPageNo(pageNoStr);
        Page page = new Page(pageNo);
        page.setPageNo(pageNo);

        // 查询条件回填
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("id", id == null ? "" : id.toString());
        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("type", type == null ? BookType.ALL.getValue() : type);

        if (id == null || id <= 0) {
            id = null;
        }
        if (StringUtils.isBlank(name)) {
            name = null;
        }
        if (type == null || BookType.ALL.getValue() == type.byteValue()) {
            type = null;
        }

        PagedList<Book> pagedList = bookService.queryByConditions(id, name, type, page);
        if (pagedList != null) {
            model.addAttribute("list", pagedList.getList());
            model.addAttribute("page", pagedList.getPage());
        }
        return "shop/book_list";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String queryCart(@RequestParam(value = "page", required = false) String pageNoStr, HttpSession session,
                            ModelMap model) {

        supportEnum(model);
        supportJavaMethod(model);

        // PAGE
        Integer pageNo = getPageNo(pageNoStr);
        Page page = new Page(pageNo);
        page.setPageNo(pageNo);

        // 查询条件回填
        model.addAttribute("pageNo", pageNo);

        ShopCartDetails scd = shopService.queryByCartId(session.getId(), page);
        if (!scd.getBooks().isEmpty()) {
            page.setTotalCount(scd.getBooks().size());
            model.addAttribute("list", scd.getBooks());
            model.addAttribute("page", page);
            model.addAttribute("sumPrice", scd.getSumPrice());
        }
        return "shop/cart_list";
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    @ResponseBody
    public String addToCart(@RequestParam(value = "ids", required = false) String ids, HttpSession session) {

        // 参数处理
        String[] strIds = ids.split(",");
        Long[] longIds = new Long[strIds.length];
        for (int i = 0; i < strIds.length; i++) {
            longIds[i] = Long.parseLong(strIds[i]);
        }

        return shopService.addToCart(session.getId(), longIds);
    }

    @RequestMapping(value = "/book/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@RequestParam(value = "ids", required = false) String ids, HttpSession session) {

        // 参数处理
        String[] strIds = ids.split(",");
        Long[] bookIds = new Long[strIds.length];
        for (int i = 0; i < strIds.length; i++) {
            bookIds[i] = Long.parseLong(strIds[i]);
        }

        return shopService.deleteFromCart(session.getId(), bookIds);
    }

}
