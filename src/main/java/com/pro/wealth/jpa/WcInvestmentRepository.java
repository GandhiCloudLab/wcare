package com.pro.wealth.jpa;

import com.pro.wealth.entity.WcInvestmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WcInvestmentRepository extends CrudRepository<WcInvestmentEntity, Integer> {

    List<WcInvestmentEntity> findByWcGoalId(int wcGoalId);

}

