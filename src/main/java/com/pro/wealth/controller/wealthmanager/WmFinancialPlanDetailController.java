package com.pro.wealth.controller.wealthmanager;

import javax.servlet.http.HttpServlet;

import com.pro.wealth.model.GoalInfo;
import com.pro.wealth.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WmFinancialPlanDetailController extends HttpServlet {

    @Autowired
    GoalService goalService;

    @RequestMapping(value="/WmFinancialPlanDetail", method = RequestMethod.GET)
    public String fpDetail(ModelMap model, @RequestParam int goalId){

        GoalInfo goalInfo = goalService.findInfo(goalId);
        model.addAttribute("mainData", goalInfo);

        return "/wm/wm_fp_detail";
    }

}

