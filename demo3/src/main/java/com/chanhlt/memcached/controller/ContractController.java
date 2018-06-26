package com.chanhlt.memcached.controller;

import com.chanhlt.memcached.repository.ContractRepo;
import com.chanhlt.memcached.service.ContractTypeService;

import com.chanhlt.memcached.entiry.*;

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
    ContractTypeService contractTypeService;

    @Autowired
    ContractRepo contractRepo;

    @GetMapping("/add")
    public String addForm() {
        return "add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addContract(@RequestParam("name") String name, @RequestParam("type") int type) {

        Contract contract = new Contract();
        contract.setName(name);
        contract.setType(type);
        Contract data = contractRepo.save(contract);

        return new ResponseEntity<Contract>(data, HttpStatus.OK);
    }

    @GetMapping("/contract/types")
    @ResponseBody
    public ResponseEntity<?> contractTypes() {
        Object data = contractTypeService.getAllContractTypes();
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }

}