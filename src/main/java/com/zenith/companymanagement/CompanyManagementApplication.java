package com.zenith.companymanagement;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zenith.companymanagement.views.EmployeeDisplay;

@SpringBootApplication
public class CompanyManagementApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementApplication.class, args);
	}
}
