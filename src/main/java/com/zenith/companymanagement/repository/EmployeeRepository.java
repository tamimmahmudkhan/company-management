package com.zenith.companymanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zenith.companymanagement.domain.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
