package com.chanhlt.memcached.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.chanhlt.memcached.entiry.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

@Service
@Transactional
public class EmployeeService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    Jedis redis;

    public List<Employee> getTop100() {

        List<String> employeeIds = new ArrayList<>();
        List<Double> scores = new ArrayList<>();

        Set<Tuple> tuples = redis.zrevrangeWithScores("top-100", 0, 99);
        for (Tuple tuple : tuples) {
            employeeIds.add(tuple.getElement());
            scores.add(tuple.getScore());
        }

        String ids = String.join(",", employeeIds);
        String scoreOrder = scores.stream().map(s -> String.valueOf(s)).collect(Collectors.joining(","));

        List<Employee> employees = em.createQuery("select e from Employee as e where e.id IN (" + ids
                + ") order by field(e.score, " + scoreOrder + "), e.id ", Employee.class).getResultList();

        // List<Employee> employees = em.createQuery("select e from Employee as e order by e.score desc, e.id ", Employee.class)
        // .setMaxResults(100).setFirstResult(0).getResultList();

        return employees;

    }

    public Employee updateScore(int employeeId, double score){
        Employee employee = em.find(Employee.class, employeeId);
        employee.setScore(score);
        em.flush();
        redis.zadd("top-100", employee.getScore(), String.valueOf(employee.getId()));
        // redis.zremrangeByRank("top-100", 0, -101);
        return employee;
    }
}