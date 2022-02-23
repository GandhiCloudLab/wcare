package com.pro.wealth.service;

import com.pro.wealth.common.LogUtil;
import com.pro.wealth.entity.WcInvestmentEntity;
import com.pro.wealth.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Configuration
@Service
public class InvestmentService {

    @Autowired
    WcInvestmentService wcInvestmentService;

    public InvestmentInfo create(WcInvestment wcInvestment) {
        LogUtil.log("InvestmentService  create Started ");

        InvestmentInfo result = wcInvestmentService.create (wcInvestment);

        LogUtil.log("InvestmentService  create Completed : " + result);

        return result;
    }
}
