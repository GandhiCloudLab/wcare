package com.pro.wealth.service;

import com.pro.wealth.common.LogUtil;
import com.pro.wealth.entity.WcInvestmentEntity;
import com.pro.wealth.jpa.WcInvestmentRepository;
import com.pro.wealth.model.InvestmentInfo;
import com.pro.wealth.model.WcInvestment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WcInvestmentService {

    @Autowired
    WcInvestmentRepository repository;

    public List<WcInvestmentEntity> findAll() {
        List<WcInvestmentEntity> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));
        return list;
    }

    public InvestmentInfo create(WcInvestment wcInvestment) {
        WcInvestmentEntity entity = createEntity(wcInvestment);
        entity = repository.save(entity);
        LogUtil.log("WcInvestmentService : Investment created : " + entity.getId());
        InvestmentInfo info = createInfo(entity);
        return info;
    }

    public WcInvestmentEntity update(WcInvestmentEntity entity) {
        LogUtil.log("WcInvestmentService : update : " + entity.getId());

        repository.save(entity);
        entity = find(entity.getId());
        return entity;
    }

    public WcInvestmentEntity find(Integer id) {
        return repository.findById(id).get();
    }

    public List<WcInvestmentEntity> findByWcGoalId(Integer wcGoalId) {
        return repository.findByWcGoalId(wcGoalId);
    }

    public WcInvestmentEntity delete(int id) {
        LogUtil.log("WcInvestmentService : delete : " + id);

        WcInvestmentEntity entity = find(id);
        if(entity != null){
            repository.delete(entity);
        }
        return entity;
    }

    public void deleteByGoalId(int wcGoalId) {
        LogUtil.log("WcInvestmentService : deleteByGoalId : " + wcGoalId);
        repository.findByWcGoalId(wcGoalId).forEach(listItem -> repository.delete(listItem));
    }

    public WcInvestmentEntity createEntity(WcInvestment wcInvestment) {
        WcInvestmentEntity entity = new WcInvestmentEntity();
        entity.setId(wcInvestment.getId());
        entity.setWcGoalId(wcInvestment.getWcGoalId());
        entity.setInvestmentDate(wcInvestment.getInvestmentDate());
        entity.setInvestmentAmount(wcInvestment.getInvestmentAmount());
        entity.setStockAmount(wcInvestment.getStockAmount());
        entity.setMutualFundAmount(wcInvestment.getMutualFundAmount());
        entity.setFixedDepositAmount(wcInvestment.getFixedDepositAmount());
        return entity;
    }

    public InvestmentInfo createInfo(WcInvestmentEntity entity) {
        InvestmentInfo investmentInfo = new InvestmentInfo();
        investmentInfo.setId(entity.getId());
        investmentInfo.setWcGoalId(entity.getWcGoalId());
        investmentInfo.setInvestmentDate(String.valueOf(entity.getInvestmentDate()));
        investmentInfo.setInvestmentAmount(String.valueOf(entity.getInvestmentAmount()));
        investmentInfo.setStockAmount(String.valueOf(entity.getStockAmount()));
        investmentInfo.setMutualFundAmount(String.valueOf(entity.getMutualFundAmount()));
        investmentInfo.setFixedDepositAmount(String.valueOf(entity.getFixedDepositAmount()));
        return investmentInfo;
    }

}
