package com.pro.wealth.jpa;

import com.pro.wealth.entity.WcWealthManagerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WcWealthManagerRepository extends CrudRepository<WcWealthManagerEntity, Integer> {

    public WcWealthManagerEntity findOneByWcUserId(Integer wcUserId);


}

