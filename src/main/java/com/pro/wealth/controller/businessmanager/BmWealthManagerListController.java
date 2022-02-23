package com.pro.wealth.controller.businessmanager;

import java.util.List;

import com.pro.wealth.model.WcWealthManager;
import com.pro.wealth.service.WcWealthManagerService;
import com.pro.wealth.service.WealthManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BmWealthManagerListController {

    @Autowired
    WcWealthManagerService wealthManagerService;

    @RequestMapping(value="/BmWealthManagerList", method = RequestMethod.GET)
    public String findAll(Model model) {
        List<WcWealthManager> list = wealthManagerService.findAll();
        model.addAttribute("mainData", list);
        return "bm/bm_wm_list";
    }
    
}

