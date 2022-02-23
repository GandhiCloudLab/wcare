package com.pro.wealth.controller.wealthmanager;

import com.pro.wealth.model.WcCustomer;
import com.pro.wealth.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WmProfileListController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/WmProfileList", method = RequestMethod.GET)
    public String load(ModelMap model, HttpServletRequest request){

        int customerId = (int) request.getSession().getAttribute("customerId");

        WcCustomer wcCustomer =  customerService.find(customerId);
        model.addAttribute("mainData", wcCustomer);
        return "/wm/wm_profile_list";
    }

}

