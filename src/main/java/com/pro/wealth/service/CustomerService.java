package com.pro.wealth.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pro.wealth.common.LogUtil;
import com.pro.wealth.entity.WcCustomerEntity;
import com.pro.wealth.model.LoginInfo;
import com.pro.wealth.model.WcCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;


@Configuration
@Service
public class CustomerService {

    @Autowired
    WcCustomerService wcCustomerService;

    public WcCustomer find(int customerId) {
        LogUtil.log("CustomerService  find Started :customerId: " + customerId);

        WcCustomer result = (WcCustomer) wcCustomerService.find(customerId);

        LogUtil.log("CustomerService  find Completed : " + result);

        return result;
    }

    public List<WcCustomer> findAll() {
        LogUtil.log("CustomerService  findAll Started ");

        List<WcCustomer> result = (List<WcCustomer>) wcCustomerService.findAll ();

        LogUtil.log("CustomerService  findAll Completed : " + result);

        return result;
    }
}
