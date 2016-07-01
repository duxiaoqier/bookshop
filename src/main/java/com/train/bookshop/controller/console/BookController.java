package com.train.bookshop.controller.console;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.train.bookshop.bean.page.Page;
import com.train.bookshop.bean.page.PagedList;
import com.train.bookshop.controller.AbstractController;
import com.train.bookshop.dto.Book;
import com.train.bookshop.enums.BookType;
import com.train.bookshop.service.BookQueryService;

/**
 * 查询任务执行信息
 * 
 * @author dup, 2016-03-04
 */
@Controller
@RequestMapping("/protect/console")
public class BookController extends AbstractController {

    @Autowired
    private BookQueryService bookQueryService;

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
    public ModelAndView query(@PathVariable(value = "jobId") Long jobId, ModelMap model) {

        supportEnum(model);
        supportJavaMethod(model);

        model.addAttribute("bookDetail", "");
        return new ModelAndView("console/job_detail");
    }

}
