package com.chanhlt.memcached.controller;

import com.chanhlt.memcached.repository.ContractRepo;
import com.chanhlt.memcached.repository.EmployeeRepo;
import com.chanhlt.memcached.service.ContractTypeService;

import java.util.List;

import javax.transaction.Transactional;

import com.chanhlt.memcached.entiry.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContractController {

    @Autowired
    ContractTypeService contractTypeService;

    @Autowired
    ContractRepo contractRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    // @GetMapping("/add")
    // public String addForm() {
    // return "add";
    // }

    // @PostMapping("/add")
    // @ResponseBody
    // public ResponseEntity<?> addContract(@RequestParam("name") String name,
    // @RequestParam("type") int type) {

    // Contract contract = new Contract();
    // contract.setName(name);
    // contract.setType(type);
    // Contract data = contractRepo.save(contract);

    // return new ResponseEntity<Contract>(data, HttpStatus.OK);
    // }

    // @GetMapping("/contract/types")
    // @ResponseBody
    // public ResponseEntity<?> contractTypes() {
    // Object data = contractTypeService.getAllContractTypes();
    // return new ResponseEntity<Object>(data, HttpStatus.OK);
    // }

    @GetMapping("/contract/{contractId}/person-in-charge")
    public String addPersonInCharge(@PathVariable("contractId") int contractId, Model model) {
        Contract contract = contractRepo.getOne(contractId);
        model.addAttribute("employees", contract.getEmployees());
        model.addAttribute("contractId", contractId);
        return "person-in-charge";
    }

    @GetMapping("/contracts")
    public String allContracts() {
        return "all-contract";
    }

    @GetMapping("/contract-list")
    public String contractList(Model model) {
        List<Contract> contracts = contractRepo.findAll();
        model.addAttribute("contracts", contracts);
        return "contract-list";
    }

    @PostMapping("/contract/{contractId}/person-in-charge")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> addPersonInCharge(@PathVariable("contractId") int contractId, @RequestParam("userId") int userId) {

        Contract contract = contractRepo.getOne(contractId);
        Employee employee= employeeRepo.getOne(userId);
        contract.getEmployees().add(employee);
        Contract data = contractRepo.save(contract);

        return new ResponseEntity<Contract>(data, HttpStatus.OK);
    }
}