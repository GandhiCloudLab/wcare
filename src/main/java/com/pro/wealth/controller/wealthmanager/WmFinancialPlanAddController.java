package com.pro.wealth.controller.wealthmanager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.pro.wealth.common.DateUtil;
import com.pro.wealth.common.NumberUtil;
import com.pro.wealth.entity.WcGoalEntity;
import com.pro.wealth.model.WcGoal;
import com.pro.wealth.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class WmFinancialPlanAddController {

    @Autowired
    GoalService goalService;

    @RequestMapping(value="/WmFinancialPlanAdd/goalAdd", method = RequestMethod.GET)
    public String goalAdd(ModelMap model, HttpServletRequest request, @RequestParam String goalReference,
                          @RequestParam String goalDescription,
                          @RequestParam String targetDate,
                          @RequestParam String targetAmount,
                          @RequestParam int wealthManagerId) {

        int customerId = (int) request.getSession().getAttribute("customerId");


        WcGoalEntity wcGoalEntity = new WcGoalEntity();

        wcGoalEntity.setWcCustomerId(customerId);
        wcGoalEntity.setWcWealthManagerId(wealthManagerId);

        wcGoalEntity.setGoalReference(goalReference);
        wcGoalEntity.setGoalDesc(goalDescription);
        wcGoalEntity.setTargetDate(DateUtil.getDateDefaultToNYear(targetDate,5));
        wcGoalEntity.setTargetAmount(NumberUtil.stringToDouble(targetAmount));

        goalService.create(wcGoalEntity);

        return "WmFinancialPlanList";
    }

    @RequestMapping(value="/WmFinancialPlanAdd/loadForAdd", method = RequestMethod.POST)
    public String loadForAdd(ModelMap model, @RequestParam int goalId){
        return "/wm/wm_fp_add";
    }
}

