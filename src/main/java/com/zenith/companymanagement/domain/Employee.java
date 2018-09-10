package com.zenith.companymanagement.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Employee
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fname;
	private String lname;
	private Designation position;
	private LocalDate dob;
	private String email;
	private String password;

	private Integer workDays;
	private Integer absentDays;
	
	public Employee(String namef, String namel, Designation pos, LocalDate date, String maile, String pass, int w, int a )
	{
		fname = namef;
		lname = namel;
		position = pos;
		dob = date;
		email = maile;
		password = pass;
		workDays = w;
		absentDays = a;
	}

	public Employee() {
	}

	protected boolean canEqual(Object other) {
		return other instanceof Employee;
	}

}
