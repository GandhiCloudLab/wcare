package com.pro.wealth.jpa;

import com.pro.wealth.entity.WcBusinessManagerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WcBusinessManagerRepository extends CrudRepository<WcBusinessManagerEntity, Integer> {

    public WcBusinessManagerEntity findOneByWcUserId(Integer wcUserId);

}

