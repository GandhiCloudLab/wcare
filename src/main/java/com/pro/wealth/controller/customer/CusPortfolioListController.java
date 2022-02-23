package com.pro.wealth.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pro.wealth.model.GoalInfo;
import com.pro.wealth.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CusPortfolioListController {

    @Autowired
    GoalService goalService;

    @RequestMapping(value="/CusPortfolioList", method = RequestMethod.GET)
    public String fpList(ModelMap model, HttpServletRequest request) {

        int customerId = (int) request.getSession().getAttribute("customerId");

        List<GoalInfo> list = (List<GoalInfo>) goalService.findInfoListByCustomerId(customerId);
        model.addAttribute("mainData", list);

        return "/cus/cus_portfolio_list";
    }

}

