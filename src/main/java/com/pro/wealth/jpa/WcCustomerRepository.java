package com.pro.wealth.jpa;

import com.pro.wealth.entity.WcCustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WcCustomerRepository extends CrudRepository<WcCustomerEntity, Integer> {

    public WcCustomerEntity findOneByWcUserId(Integer wcUserId);

}

