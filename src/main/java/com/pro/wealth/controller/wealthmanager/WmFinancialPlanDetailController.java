package com.pro.wealth.controller.wealthmanager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.pro.wealth.model.GoalInfo;
import com.pro.wealth.service.GoalService;
import com.pro.wealth.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WmFinancialPlanDetailController extends HttpServlet {

    @Autowired
    GoalService goalService;

    @Autowired
    InvestmentService investmentService;

    @RequestMapping(value="/WmFinancialPlanDetail", method = RequestMethod.GET)
    public String fpDetail(ModelMap model, @RequestParam int goalId){
        GoalInfo goalInfo = goalService.findInfo(goalId);
        model.addAttribute("mainData", goalInfo);
        return "/wm/wm_fp_detail";
    }

    @RequestMapping(value="/WmFinancialPlanDetail/deleteInvestment", method = RequestMethod.GET)
    public String deleteInvestment(ModelMap model, @RequestParam int goalId,  @RequestParam int investmentId,  HttpServletRequest request){

        investmentService.delete(investmentId);

        GoalInfo goalInfo = goalService.findInfo(goalId);
        model.addAttribute("mainData", goalInfo);
        return "/wm/wm_fp_detail";
    }

    public String commonSteps(ModelMap model, @RequestParam int customerId){
        List list = (List) goalService.findInfoListByCustomerId(customerId);
        model.addAttribute("mainData", list);
        return "wm/wm_fp_list";
    }
}

