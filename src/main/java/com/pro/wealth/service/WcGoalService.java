package com.pro.wealth.service;

import com.pro.wealth.common.ConversionUtil;
import com.pro.wealth.common.DateUtil;
import com.pro.wealth.common.LogUtil;
import com.pro.wealth.common.NumberUtil;
import com.pro.wealth.entity.WcGoalEntity;
import com.pro.wealth.entity.WcInvestmentEntity;
import com.pro.wealth.jpa.WcGoalRepository;
import com.pro.wealth.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WcGoalService {

    public static final Logger logger = LoggerFactory.getLogger(WcGoalService.class);

    @Autowired
    WcInvestmentService investmentService;

    @Autowired
    WcGoalRepository repository;

    @Autowired
    FinanceQuoteService financeQuoteService;

    public List<WcGoalEntity> findAll() {
        List<WcGoalEntity> list = new ArrayList();
        GoalInfo goalInfo;
        repository.findAll().forEach(listItem -> {
            list.add(listItem);
        });
        return list;
    }

    public List<GoalInfo> findInfoListByCustomerId(Integer wcCustomerId) {
        List<GoalInfo> list = new ArrayList();
        GoalTotalData goalTotalData2 = new GoalTotalData();

        repository.findByWcCustomerId(wcCustomerId).forEach(listItem -> {
            List<WcInvestmentEntity> investments = investmentService.findByWcGoalId(listItem.getId());

            list.add(createGoalInfo(listItem, investments, goalTotalData2));
        });

        List<GraphDataBar> globalListBar = createGraphDataBar(goalTotalData2);
        List<GraphDataLine> globalListLine = createGraphDataLine(goalTotalData2);

        List<Object> resultList = new ArrayList<>();
        resultList.add(list);
        resultList.add(globalListBar);
        resultList.add(globalListLine);
        LogUtil.log("WcGoalService findInfoListByCustomerId goalTotalData2 1--> "+ ConversionUtil.objectToJsonString(resultList));
        LogUtil.log("WcGoalService findInfoListByCustomerId goalTotalData2 2--> "+ ConversionUtil.objectToJsonString(list));
        LogUtil.log("WcGoalService findInfoListByCustomerId goalTotalData2 3--> "+ ConversionUtil.objectToJsonString(globalListBar));
        LogUtil.log("WcGoalService findInfoListByCustomerId goalTotalData2 4--> "+ ConversionUtil.objectToJsonString(globalListLine));

        return list;
    }

    public GoalInfo findInfo(Integer id) {
        WcGoalEntity wcGoalEntity = repository.findById(id).get();

        List<WcInvestmentEntity> investments = investmentService.findByWcGoalId(wcGoalEntity.getId());

        System.out.println("findInfo investments.size--> "+ investments.size());

        GoalInfo goalInfo = createGoalInfo(wcGoalEntity, investments, null);
        return goalInfo;
    }

    public Object create(WcGoal entity) {
        LogUtil.log("WcGoalService : create : " + entity.getGoalReference());

        //Set startDate
        entity.setStartDate(new Date());

        //Create Goal
        Object result = null;
        entity = repository.save((WcGoalEntity) entity);
        LogUtil.log("WcGoalService : Goal created : " + entity.getId());

        return entity;
    }

    public WcGoalEntity update(WcGoalEntity entity) {
        LogUtil.log("WcGoalService : update : " + entity.getId());

        repository.save(entity);
        entity = (WcGoalEntity) find(entity.getId());
        return entity;
    }

    public WcGoalEntity find(Integer id) {
        return repository.findById(id).get();
    }

    public WcGoal delete(int id) {
        LogUtil.log("WcGoalService : delete : " + id);

        WcGoalEntity entity = (WcGoalEntity) find(id);
        if(entity != null){
            investmentService.deleteByGoalId(id);
            repository.delete(entity);
        }
        return entity;
    }

    public GoalInfo createGoalInfo(WcGoalEntity wcGoalEntity, List<WcInvestmentEntity> investments, GoalTotalData goalTotalData2) {
        InvestmentInfo investmentInfo;
        List<InvestmentInfo> list = new ArrayList<>();

        GoalInfo goalInfo = new GoalInfo();
        goalInfo.setInvestments(list);

        goalInfo.setId(wcGoalEntity.getId());
        goalInfo.setWcCustomerId(wcGoalEntity.getWcCustomerId());
        goalInfo.setWcWealthManagerId(wcGoalEntity.getWcWealthManagerId());
        goalInfo.setGoalReference(wcGoalEntity.getGoalReference());
        goalInfo.setGoalDesc(wcGoalEntity.getGoalDesc());

        goalInfo.setTargetAmount(String.valueOf((long) wcGoalEntity.getTargetAmount()));
        goalInfo.setTargetDate(DateUtil.convertMMMYYY(wcGoalEntity.getTargetDate()));

        double totalInvestmentAmount = 0;
        double totalCurrentValue = 0;

        GoalTotalData goalTotalData = new GoalTotalData();

        for (WcInvestmentEntity wcInvestmentEntity : investments) {
            investmentInfo = createInvestmentInfo(goalInfo, wcInvestmentEntity, goalTotalData, goalTotalData2);
            list.add(investmentInfo);

            totalInvestmentAmount += wcInvestmentEntity.getInvestmentAmount();
            totalCurrentValue +=  investmentInfo.getCurrentValueTotal();
        }

        populateGoalInfo(goalInfo, totalInvestmentAmount, totalCurrentValue, wcGoalEntity.getTargetAmount(), wcGoalEntity.getStartDate(), wcGoalEntity.getTargetDate());

        goalInfo.setGraphDataBar(createGraphDataBar(goalTotalData));
        goalInfo.setGraphDataLine(createGraphDataLine(goalTotalData));

        return goalInfo;
    }

    public InvestmentInfo createInvestmentInfo(GoalInfo goalInfo, WcInvestmentEntity wcInvestmentEntity, GoalTotalData goalTotalData, GoalTotalData goalTotalData2) {
        InvestmentInfo investmentInfo = new InvestmentInfo();

        investmentInfo.setId(wcInvestmentEntity.getId());
        investmentInfo.setWcGoalId(goalInfo.getId());
        investmentInfo.setInvestmentDate(DateUtil.convertDDMMMYYY(wcInvestmentEntity.getInvestmentDate()));
        investmentInfo.setInvestmentAmount(String.valueOf((long) wcInvestmentEntity.getInvestmentAmount()));

        investmentInfo.setStockAmount((String.valueOf((long) wcInvestmentEntity.getStockAmount())));
        investmentInfo.setMutualFundAmount((String.valueOf((long) wcInvestmentEntity.getMutualFundAmount())));
        investmentInfo.setFixedDepositAmount((String.valueOf((long) wcInvestmentEntity.getFixedDepositAmount())));

        //Populate Quote current and total values
        financeQuoteService.populateCurrentQuote(wcInvestmentEntity, investmentInfo);

        goalTotalData.addValues(wcInvestmentEntity.getStockAmount(), wcInvestmentEntity.getMutualFundAmount(), wcInvestmentEntity.getFixedDepositAmount(),
                investmentInfo.getCurrentValueStockAmount(),  investmentInfo.getCurrentValueMutualFundAmount(), investmentInfo.getCurrentValueFixedDepositAmount());

        if (goalTotalData2 != null) {
            goalTotalData2.addValues(wcInvestmentEntity.getStockAmount(), wcInvestmentEntity.getMutualFundAmount(), wcInvestmentEntity.getFixedDepositAmount(),
                    investmentInfo.getCurrentValueStockAmount(),  investmentInfo.getCurrentValueMutualFundAmount(), investmentInfo.getCurrentValueFixedDepositAmount());
        }

        return investmentInfo;
    }

    public void populateGoalInfo(GoalInfo goalInfo, double totalInvestmentAmount, double totalCurrentValue, double targetAmount, Date startDate, Date targetDate) {

        int totalPercentageDifference = NumberUtil.percentage(targetAmount, totalCurrentValue);

        int totalYears = DateUtil.dateDiffInYears(targetDate, startDate);
        int completedYears = DateUtil.dateDiffInYears(new Date(), startDate);
        String achievementString = "( "+ totalPercentageDifference + " % goal reached in " + completedYears + "/" + totalYears + " years )";

        goalInfo.setCompletionPercentage(NumberUtil.createPercentageCompletionList(totalPercentageDifference));
        goalInfo.setGoalAchievementString(achievementString);
        goalInfo.setTotalInvestmentAmount(String.valueOf((long)totalInvestmentAmount));
        goalInfo.setInvestmentCurrentValue(String.valueOf((long)totalCurrentValue));
        //TODO: To be implemented
        goalInfo.setCurrency("INR");
    }

    public List<GraphDataBar> createGraphDataBar(GoalTotalData goalTotalData) {
        List<GraphDataBar> graphDataList = new ArrayList<>();

        GraphDataBar graphData = new GraphDataBar(goalTotalData.getInitialStock(), goalTotalData.getInitialMutual(),  goalTotalData.getInitialFd(),  "Investment Amount");
        graphDataList.add(graphData);

        graphData = new GraphDataBar(goalTotalData.getCurrStock(), goalTotalData.getCurrMutual(),  goalTotalData.getCurrFd(),  "Current Amount");
        graphDataList.add(graphData);

        return graphDataList;
    }

    public List<GraphDataLine> createGraphDataLine(GoalTotalData goalTotalData) {
        List<GraphDataLine> graphDataList = new ArrayList<>();

        GraphDataLine graphData = new GraphDataLine(goalTotalData.getInitialStock(), goalTotalData.getCurrStock(), "Stock");
        graphDataList.add(graphData);

        graphData = new GraphDataLine(goalTotalData.getInitialMutual(), goalTotalData.getCurrMutual(),   "Mutual Fund");
        graphDataList.add(graphData);

        graphData = new GraphDataLine(goalTotalData.getInitialFd(), goalTotalData.getCurrFd(),   "Fixed Deposit");
        graphDataList.add(graphData);

        return graphDataList;
    }
}
