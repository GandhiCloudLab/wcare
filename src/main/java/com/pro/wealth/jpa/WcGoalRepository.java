package com.pro.wealth.jpa;

import com.pro.wealth.entity.WcGoalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WcGoalRepository extends CrudRepository<WcGoalEntity, Integer> {

    List<WcGoalEntity> findByWcCustomerId(int wcCustomerId);

}

