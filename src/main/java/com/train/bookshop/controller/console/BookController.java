package com.train.bookshop.controller.console;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.bean.page.PagedList;
import com.train.bookshop.controller.AbstractController;
import com.train.bookshop.dto.Book;
import com.train.bookshop.enums.BookType;
import com.train.bookshop.service.BookService;

/**
 * 查询任务执行信息
 * 
 * @author dup, 2016-03-04
 */
@Controller
@RequestMapping("/protect/console")
public class BookController extends AbstractController {

    @Autowired
    private BookService bookQueryService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ModelAndView query(@RequestParam(value = "id", required = false) Long id,
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

        PagedList<Book> pagedList = bookQueryService.queryByConditions(id, name, type, page);
        if (pagedList != null) {
            model.addAttribute("list", pagedList.getList());
            model.addAttribute("page", pagedList.getPage());
        }
        return new ModelAndView("console/book_list");
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public ModelAndView query(@PathVariable(value = "bookId") Long bookId, ModelMap model) {

        supportEnum(model);
        supportJavaMethod(model);

        Book book = bookQueryService.queryByPrimaryKey(bookId);
        model.addAttribute("bookDetail", book);
        return new ModelAndView("console/book_update");
    }

    @RequestMapping(value = "/book/insert", method = RequestMethod.GET)
    public ModelAndView insertBook(ModelMap model) {

        supportEnum(model);
        supportJavaMethod(model);

        return new ModelAndView("console/book_add");
    }

    @RequestMapping(value = "/book/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@RequestParam(value = "ids", required = false) String ids) {

        // 参数处理
        String[] strIds = ids.split(",");
        Long[] longIds = new Long[strIds.length];
        for (int i = 0; i < strIds.length; i++) {
            longIds[i] = Long.parseLong(strIds[i]);
        }

        bookQueryService.delete(longIds);
        return "ok";
    }

    @RequestMapping(value = "/book/update", method = RequestMethod.POST)
    public ModelAndView update(Book book, ModelMap model) {
        String msg = bookQueryService.update(book);
        model.addAttribute("msg", msg);
        return new ModelAndView("console/update_result");
    }

    @RequestMapping(value = "/book/update", method = RequestMethod.GET)
    public String updateToShow() {
        return "redirect:/protect/console/book";
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public ModelAndView add(Book book, ModelMap model) {
        String msg = bookQueryService.add(book);
        model.addAttribute("msg", msg);
        return new ModelAndView("console/add_result");
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.GET)
    public String addToShow() {
        return "redirect:/protect/console/book";
    }
}
