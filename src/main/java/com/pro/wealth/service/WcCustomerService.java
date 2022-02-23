package com.pro.wealth.service;

import com.pro.wealth.common.CommonConstants;
import com.pro.wealth.common.DateUtil;
import com.pro.wealth.common.LogUtil;
import com.pro.wealth.entity.*;
import com.pro.wealth.entity.WcCustomerEntity;
import com.pro.wealth.jpa.WcCustomerRepository;
import com.pro.wealth.model.CustomError;
import com.pro.wealth.model.WcCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WcCustomerService {

    @Autowired
    WcUsersService usersService;

    @Autowired
    WcCustomerRepository repository;

    @Autowired
    ImageService imageService;

    @Autowired
    WcWealthManagerService  wealthManagerService;

    public List<WcCustomer> findAll() {
        List<WcCustomer> list = new ArrayList();
        repository.findAll().forEach(listItem -> {
                    list.add(listItem);
                    listItem.setStartDateString(DateUtil.convertMMMYYY(listItem.getStartDate()));
                    listItem.setImage(imageService.createImageUrl(listItem.getGender(), listItem.getId(), true));
                    populateWealthManagerName(listItem);
                }
        );
        return list;
    }

    public Object create(WcCustomerEntity wcCustomerEntity) {
        Object result;

        LogUtil.log("WcCustomerService : create : " + wcCustomerEntity.getEmailId());

        //Create User
        WcUsersEntity wcUsersEntity = new WcUsersEntity(wcCustomerEntity.getEmailId(), "wc", wcCustomerEntity.getEmailId(), CommonConstants.ROLE_CUSTOMER);

        result = usersService.create(wcUsersEntity);

        if (result instanceof CustomError) {
            LogUtil.log("WcCustomerService : User not created");
        } else {
            //Copy the userId from the newly created entity
            wcUsersEntity = (WcUsersEntity) result;
            wcCustomerEntity.setWcUserId(wcUsersEntity.getId());

            //Create Customer
            wcCustomerEntity = repository.save(wcCustomerEntity);
            LogUtil.log("WcCustomerService : User created : " + wcUsersEntity.getId());
            result = wcCustomerEntity;
        }

        return result;
    }

    public WcCustomerEntity update(WcCustomerEntity wcCustomerEntity) {
        LogUtil.log("WcCustomerService : update : " + wcCustomerEntity.getId());

        //Update wcusers
        usersService.update(wcCustomerEntity.getWcUserId(), wcCustomerEntity.getEmailId());;

        repository.save(wcCustomerEntity);
        wcCustomerEntity = find(wcCustomerEntity.getId());
        return wcCustomerEntity;
    }

    public WcCustomerEntity find(Integer id) {
        WcCustomerEntity wcCustomerEntity = repository.findById(id).get();
        populateWealthManagerName(wcCustomerEntity);
        return wcCustomerEntity;
    }

    private void populateWealthManagerName(WcCustomerEntity wcCustomerEntity) {
        String welathManagerName = "";
        if (wcCustomerEntity.getWcWealthManagerId() > 0) {
            WcWealthManagerEntity wcWealthManagerEntity = wealthManagerService.find(wcCustomerEntity.getWcWealthManagerId());
            if (wcWealthManagerEntity != null) {
                welathManagerName = wcWealthManagerEntity.getFirstName() + " " + wcWealthManagerEntity.getLastName();
            }
            wcCustomerEntity.setWealthManagerName(welathManagerName);
        }
    }

    public WcCustomerEntity delete(int id) {

        LogUtil.log("WcCustomerService : delete : " + id);

        WcCustomerEntity wcCustomerEntity = find(id);
        if(wcCustomerEntity != null){
            usersService.delete(wcCustomerEntity.getWcUserId());
            repository.delete(wcCustomerEntity);
        }
        return wcCustomerEntity;
    }

    public WcCustomerEntity findOneByWcUserId(Integer id) {
        return repository.findOneByWcUserId(id);
    }

}
