package com.jrp.pma.services;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
	
	@Autowired
    EmployeeRepository empRepo;
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}

	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}

	public Iterable<Employee> getAll(Pageable pageAndSize) {
		return empRepo.findAll(pageAndSize);
	}
}