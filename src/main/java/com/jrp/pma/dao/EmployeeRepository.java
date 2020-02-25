package com.jrp.pma.dao;

import com.jrp.pma.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();
}
