package com.pro.wealth.controller.wealthmanager;

import java.util.Date;

import com.pro.wealth.common.NumberUtil;
import com.pro.wealth.common.RandomUtil;
import com.pro.wealth.model.GoalInfo;
import com.pro.wealth.model.WcInvestment;
import com.pro.wealth.service.GoalService;
import com.pro.wealth.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WmInvestmentAddController {

    @Autowired
    GoalService goalService;

    @Autowired
    InvestmentService investmentService;

    @RequestMapping(value="/WmInvestmentAdd/investmentAdd", method = RequestMethod.POST)
    public String investmentAdd(ModelMap model, @RequestParam int goalId,  @RequestParam String investmentAmountString){

        System.out.println("investmentAdd goalId :  " + goalId);
        double investmentAmount = NumberUtil.stringToDouble(investmentAmountString);

        WcInvestment wcInvestment = new WcInvestment();
        wcInvestment.setWcGoalId(goalId);
        wcInvestment.setInvestmentDate(new Date());
        wcInvestment.setInvestmentAmount(investmentAmount);
        fillInvestments(wcInvestment);

        investmentService.create(wcInvestment);

        GoalInfo goalInfo = goalService.findInfo(goalId);
        model.addAttribute("mainData", goalInfo);

        return "/wm/wm_fp_detail";
    }

    @RequestMapping(value="/WmInvestmentAdd", method = RequestMethod.GET)
    public String loadForAdd(Model model, @RequestParam int goalId, HttpServletRequest request){
        GoalInfo goalInfo = goalService.findInfo(goalId);
        model.addAttribute("mainData", goalInfo);
        return "/wm/wm_investement_add";
    }

    private void fillInvestments(WcInvestment wcInvestment) {
        double investmentAmount = wcInvestment.getInvestmentAmount();

        int firstRandom = RandomUtil.getRandomInRange(30,50);
        int secondRandom = RandomUtil.getRandomInRange(30,40);

        long firstValue = (long) (investmentAmount * firstRandom ) / 100;
        long secondValue = (long) (investmentAmount * secondRandom ) / 100;
        long thirdValue = (long) investmentAmount - (firstValue + secondValue);

        wcInvestment.setMutualFundAmount(firstValue);
        wcInvestment.setStockAmount(secondValue);
        wcInvestment.setFixedDepositAmount(thirdValue);
    }
}

