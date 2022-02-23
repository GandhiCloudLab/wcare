package com.pro.wealth.service;

import com.pro.wealth.common.CommonConstants;
import com.pro.wealth.common.DateUtil;
import com.pro.wealth.common.LogUtil;
import com.pro.wealth.entity.WcUsersEntity;
import com.pro.wealth.entity.WcWealthManagerEntity;
import com.pro.wealth.jpa.WcWealthManagerRepository;
import com.pro.wealth.model.CustomError;
import com.pro.wealth.model.WcWealthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WcWealthManagerService {

    @Autowired
    WcUsersService usersService;

    @Autowired
    WcWealthManagerRepository repository;

    @Autowired
    ImageService imageService;

    public List<WcWealthManager> findAll() {
        List<WcWealthManager> list = new ArrayList();
        repository.findAll().forEach(listItem -> {
                    list.add(listItem);
                    listItem.setStartDateString(DateUtil.convertMMMYYY(listItem.getStartDate()));
                    listItem.setImage(imageService.createImageUrl(listItem.getGender(), listItem.getId(), true));
                }
        );
        return list;
    }

    public Object create(WcWealthManagerEntity wcWealthManagerEntity) {
        LogUtil.log("WcWealthManagerService : create Started");
        System.out.println("WcWealthManagerService : create :" + wcWealthManagerEntity.getEmailId());

        Object result;

        //Create User
        WcUsersEntity wcUsersEntity = new WcUsersEntity(wcWealthManagerEntity.getEmailId(), "wc", wcWealthManagerEntity.getEmailId(), CommonConstants.ROLE_WEALTH_MANAGER);
        result = usersService.create(wcUsersEntity);

        if (result instanceof CustomError) {
            LogUtil.log("WcWealthManagerService : User not created");
        } else {
            //Copy the userId from the newly created entity
            wcUsersEntity = (WcUsersEntity) result;
            wcWealthManagerEntity.setWcUserId(wcUsersEntity.getId());

            //Create Customer
            wcWealthManagerEntity = repository.save(wcWealthManagerEntity);
            LogUtil.log("WcWealthManagerService : User created : " + wcUsersEntity.getId());
            result = wcWealthManagerEntity;
        }

        LogUtil.log("WcWealthManagerService : create Completed");

        return result;
    }

    public WcWealthManagerEntity update(WcWealthManagerEntity wcWealthManagerEntity) {
        System.out.println("WcWealthManagerService : update :" + wcWealthManagerEntity.getId());

        repository.save(wcWealthManagerEntity);
        wcWealthManagerEntity = find(wcWealthManagerEntity.getId());
        return wcWealthManagerEntity;
    }

    public WcWealthManagerEntity find(Integer id) {
        return repository.findById(id).get();
    }

    public WcWealthManagerEntity delete(int id) {
        System.out.println("WcWealthManagerService : delete :" + id);

        WcWealthManagerEntity wcWealthManagerEntity = find(id);
        if(wcWealthManagerEntity != null){
            usersService.delete(wcWealthManagerEntity.getWcUserId());

            repository.delete(wcWealthManagerEntity);
        }
        return wcWealthManagerEntity;
    }

    public WcWealthManagerEntity findOneByWcUserId(Integer id) {
        return repository.findOneByWcUserId(id);
    }

}
