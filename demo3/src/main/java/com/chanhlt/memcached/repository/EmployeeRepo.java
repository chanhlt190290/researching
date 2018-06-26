package com.chanhlt.memcached.repository;

import com.chanhlt.memcached.entiry.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}