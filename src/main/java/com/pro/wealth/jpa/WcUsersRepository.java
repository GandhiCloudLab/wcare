package com.pro.wealth.jpa;

import com.pro.wealth.entity.WcUsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WcUsersRepository extends CrudRepository<WcUsersEntity, Integer> {

    public WcUsersEntity findOneByEmailId(String emailId);

    public WcUsersEntity findOneByUserName(String userName);


}

