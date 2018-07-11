package com.chanhlt.memcached.controller;

import com.chanhlt.memcached.repository.ContractRepo;
import com.chanhlt.memcached.service.ContractTypeService;

import com.chanhlt.memcached.entiry.*;
import com.chanhlt.memcached.redis.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContractController {

    @Autowired
    Publisher publisher;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addContract(@RequestParam("name") String name, @RequestParam("type") int type) {
        Contract contract = new Contract();
        contract.setName(name);
        contract.setType(type);
        Contract data = contractRepo.save(contract);
        publisher.publish("There is a new contract has been added!");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Autowired
    ContractTypeService contractTypeService;

    @Autowired
    ContractRepo contractRepo;

    @GetMapping("/add")
    public String addForm() {
        return "add";
    }

    @GetMapping("/contract/types")
    @ResponseBody
    public ResponseEntity<?> contractTypes() {
        Object data = contractTypeService.getAllContractTypes();
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }

}
