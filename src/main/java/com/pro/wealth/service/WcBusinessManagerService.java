package com.pro.wealth.service;

import com.pro.wealth.entity.WcBusinessManagerEntity;
import com.pro.wealth.jpa.WcBusinessManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WcBusinessManagerService {

    @Autowired
    WcBusinessManagerRepository repository;

    public List<WcBusinessManagerEntity> findAll() {
        List<WcBusinessManagerEntity> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));
        return list;
    }

    public WcBusinessManagerEntity create(WcBusinessManagerEntity wcBusinessManagerEntity) {
        System.out.println("WcBusinessManagerService : create :" + wcBusinessManagerEntity.getFirstName());

        return repository.save(wcBusinessManagerEntity);
    }

    public WcBusinessManagerEntity update(WcBusinessManagerEntity wcBusinessManagerEntity) {
        System.out.println("WcBusinessManagerService : update :" + wcBusinessManagerEntity.getId());

        repository.save(wcBusinessManagerEntity);
        wcBusinessManagerEntity = find(wcBusinessManagerEntity.getId());
        return wcBusinessManagerEntity;
    }

    public WcBusinessManagerEntity find(Integer id) {
        return repository.findById(id).get();
    }

    public WcBusinessManagerEntity delete(int id) {
        System.out.println("WcBusinessManagerService : delete :" + id);

        WcBusinessManagerEntity wcBusinessManagerEntity = find(id);
        if(wcBusinessManagerEntity != null){
            repository.delete(wcBusinessManagerEntity);
        }
        return wcBusinessManagerEntity;
    }

    public WcBusinessManagerEntity findOneByWcUserId(Integer id) {
        return repository.findOneByWcUserId(id);
    }


}
