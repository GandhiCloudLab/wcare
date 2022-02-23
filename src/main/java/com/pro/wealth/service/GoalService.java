package com.pro.wealth.service;

import com.pro.wealth.common.LogUtil;
import com.pro.wealth.model.GoalInfo;
import com.pro.wealth.model.WcGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;


@Configuration
@Service
public class GoalService {

    @Autowired
    WcGoalService wcGoalService;

    public List<GoalInfo> findInfoListByCustomerId(int customerId) {
        LogUtil.log("GoalService  findInfoListByCustomerId Started ");

        List<GoalInfo> result = (List<GoalInfo>) wcGoalService.findInfoListByCustomerId(customerId);

        LogUtil.log("GoalService  findInfoListByCustomerId Completed : " + result);

        return result;
    }

    public GoalInfo findInfo(int goalId) {
        LogUtil.log("GoalService  findInfo Started ");

        GoalInfo result = (GoalInfo) wcGoalService.find (goalId);

        LogUtil.log("GoalService  findInfo Completed : " + result);

        return result;
    }

    public GoalInfo delete(int goalId) {
        LogUtil.log("GoalService  delete Started ");

        GoalInfo result = (GoalInfo) wcGoalService.delete (goalId);

        LogUtil.log("GoalService  delete Completed : " + result);

        return result;
    }

    public GoalInfo create(WcGoal wcGoal) {
        LogUtil.log("GoalService  create Started ");

        GoalInfo result = (GoalInfo) wcGoalService.create (wcGoal);

        LogUtil.log("GoalService  create Completed : " + result);

        return result;
    }



}
