package com.chanhlt.memcached.service;

import java.util.List;

import com.chanhlt.memcached.entiry.*;
import com.chanhlt.memcached.repository.ContractTypeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spy.memcached.MemcachedClient;

@Service
public class ContractTypeService {
    @Autowired
    MemcachedClient memcachedClient;

    @Autowired
    ContractTypeRepo contractTypeRepo;

    @SuppressWarnings("unchecked")
    public List<ContractType> getAllContractTypes() {

        List<ContractType> types = null;

        try {
            Object object = memcachedClient.get("contract-type");
            if (object != null) {
                types = (List<ContractType>) object;
                return types;
            }
        } catch (Exception ex) {
            // ex.printStackTrace();
        }

        types = contractTypeRepo.findAll();
        memcachedClient.set("contract-type", 3600, types);

        return types;

    }
}