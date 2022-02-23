package com.pro.wealth.service;

import com.pro.wealth.common.CommonConstants;
import com.pro.wealth.common.LogUtil;
import com.pro.wealth.entity.WcUsersEntity;
import com.pro.wealth.jpa.WcUsersRepository;
import com.pro.wealth.model.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WcUsersService {

    public static final Logger logger = LoggerFactory.getLogger(WcUsersService.class);

    @Autowired
    WcUsersRepository repository;

    public List<WcUsersEntity> findAll() {
        List<WcUsersEntity> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));
        return list;
    }

    public List<WcUsersEntity> findAllWithDelay(long timeout) {
        List<WcUsersEntity> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));

        try
        {
            Thread.sleep(timeout);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        return list;
    }

    public Object create(WcUsersEntity entity) {
        LogUtil.log("WcUsersService : create started : " + entity.getEmailId());

        Object result = null;

        if (findByEmail(entity.getEmailId()) != null) {
            logger.error("WcUsersService : Email Already exist " + entity.getEmailId());
            LogUtil.log("WcUsersService : Email Already exist " + entity.getEmailId());
            result = new CustomError("user with username " + entity.getEmailId() + "already exist ");
        } else {
            if (entity.getRole() == null || entity.getRole().isEmpty()) {
                entity.setRole(CommonConstants.ROLE_CUSTOMER);
            }
            entity = repository.save(entity);
            LogUtil.log("WcUsersService : User created : " + entity.getId());
            result = entity;
        }


        LogUtil.log("WcUsersService : create completed");
        return result;
    }

    public WcUsersEntity update(WcUsersEntity wcUsersEntity) {

        LogUtil.log("WcUsersService : update : " + wcUsersEntity.getId());

        repository.save(wcUsersEntity);
        wcUsersEntity = find(wcUsersEntity.getId());
        return wcUsersEntity;
    }

    public WcUsersEntity update(int id, String emailId) {

        LogUtil.log("WcUsersService : update : " + id);

        WcUsersEntity wcUsersEntity = find(id);
        wcUsersEntity.setEmailId(emailId);
        return update(wcUsersEntity);
    }

    public WcUsersEntity findByEmail(String email) {
        return repository.findOneByEmailId(email);
    }

    public WcUsersEntity findByUserName(String email) {
        return repository.findOneByUserName(email);
    }

    public WcUsersEntity find(Integer id) {
        return repository.findById(id).get();
    }

    public WcUsersEntity delete(int id) {
        WcUsersEntity wcUsersEntity = repository.findById(id).get();

        LogUtil.log("WcUsersService : delete : " + id);
        if(wcUsersEntity != null){
            repository.delete(wcUsersEntity);
        }
        return wcUsersEntity;
    }


    public WcUsersEntity fetchUserByLoginId(String loginId) {
        LogUtil.log("WcUsersService : fetchUserByLoginId started : " + loginId);

        WcUsersEntity entity = findByEmail(loginId);
        if (entity == null) {
            LogUtil.log("WcUsersService : fetchUserByLoginId : login id doesn't match with email ids ");
            entity = findByUserName(loginId);
            if (entity == null) {
                LogUtil.log("WcUsersService : fetchUserByLoginId : login id doesn't match with user name as well");
            } else {
                LogUtil.log("WcUsersService : fetchUserByLoginId : login id match with user name ");
            }
        } else {
            LogUtil.log("WcUsersService : fetchUserByLoginId : login id match with email id ");
        }
        LogUtil.log("WcUsersService : create completed");
        return entity;
    }

}
