package com.zenith.companymanagement.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.zenith.companymanagement.domain.Employee;
import com.zenith.companymanagement.repository.EmployeeRepository;

@Service
public class EmployeeManageService implements Serializable
{
	private static final long serialVersionUID = -2580181966590656786L;
	
	EmployeeRepository employeeRepository;
	

	public EmployeeManageService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> retrieveEmployees() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}
	
	public List<Employee> retrieveEmployees(int offset, int limit) {
		return retrieveEmployees().subList(offset, limit);
	}
	
	public List<Employee> safeSubList(int fromIndex, int toIndex) {
	    int size = count();
	    if (fromIndex >= size || toIndex <= 0 || fromIndex >= toIndex) {
	        return Collections.emptyList();
	    }

	    fromIndex = Math.max(0, fromIndex);
	    toIndex = Math.min(size, toIndex);

	    return retrieveEmployees().subList(fromIndex, toIndex);
	}
	
	public boolean addEmployee(Employee employee) {
		if (employee != null) {
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}
	
	public boolean removeEmployee(Employee employee) {
		if (employee != null) {
			employeeRepository.delete(employee);
			return true;
		}
		return false;
	}
	
	public int count() {
		return retrieveEmployees().size();
	}
}
