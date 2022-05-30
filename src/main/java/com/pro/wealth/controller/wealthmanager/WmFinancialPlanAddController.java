package com.pro.wealth.controller.wealthmanager;

import javax.servlet.http.HttpServletRequest;

import com.pro.wealth.common.DateUtil;
import com.pro.wealth.common.NumberUtil;
import com.pro.wealth.entity.WcGoalEntity;
import com.pro.wealth.model.WcCustomer;
import com.pro.wealth.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WmFinancialPlanAddController {

    @Autowired
    GoalService goalService;

    @RequestMapping(value="/WmFinancialPlanAdd/goalAdd", method = RequestMethod.POST)
    public String goalAdd(ModelMap model, HttpServletRequest request, @RequestParam String goalReference,
                          @RequestParam String goalDescription,
                          @RequestParam String targetDate,
                          @RequestParam String targetAmount) {

        int customerId = (int) request.getSession().getAttribute("customerId");
        int wealthManagerId= (int) request.getSession().getAttribute("wealthManagerId");

        WcGoalEntity wcGoalEntity = new WcGoalEntity();

        wcGoalEntity.setWcCustomerId(customerId);
        wcGoalEntity.setWcWealthManagerId(wealthManagerId);

        wcGoalEntity.setGoalReference(goalReference);
        wcGoalEntity.setGoalDesc(goalDescription);
        wcGoalEntity.setTargetDate(DateUtil.getDateDefaultToNYear(targetDate,5));
        wcGoalEntity.setTargetAmount(NumberUtil.stringToDouble(targetAmount));

        goalService.create(wcGoalEntity);


        List list = (List) goalService.findInfoListByCustomerId(customerId);
        model.addAttribute("mainData", list);
        return "wm/wm_fp_list";
    }

    @RequestMapping(value="/WmFinancialPlanAdd", method = RequestMethod.GET)
    public String loadForAdd(Model mode, HttpServletRequest request){
        return "wm/wm_fp_add";
    }
}
