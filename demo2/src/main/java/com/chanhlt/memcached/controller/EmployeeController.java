package com.chanhlt.memcached.controller;

import com.chanhlt.memcached.repository.EmployeeRepo;
import com.chanhlt.memcached.service.EmployeeService;

import java.util.List;

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

import redis.clients.jedis.Jedis;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    Jedis redis;

    // @GetMapping("/top-100")
    // public String top100(Model model) {
    //     List<Employee> employees = employeeService.getTop100();
    //     model.addAttribute("employees", employees);
    //     return "top-100";
    // }

    // @PostMapping("/edit/{employeeId}")
    // @ResponseBody
    // public ResponseEntity<?> udpdateScore1(@PathVariable("employeeId") int employeeId, @RequestParam("score") double score) {
    //     Employee data = employeeService.updateScore(employeeId, score);
    //     return new ResponseEntity<Employee>(data, HttpStatus.OK);
    // }

    // @GetMapping("/edit/{employeeId}")
    // @ResponseBody
    // public ResponseEntity<?> updateScore2(@PathVariable("employeeId") int employeeId, @RequestParam("score") double score) {
    //     Employee data = employeeService.updateScore(employeeId, score);
    //     return new ResponseEntity<Employee>(data, HttpStatus.OK);
    // }

}