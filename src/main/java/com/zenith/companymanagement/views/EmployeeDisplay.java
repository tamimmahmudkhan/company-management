package com.zenith.companymanagement.views;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.zenith.companymanagement.domain.Designation;
import com.zenith.companymanagement.domain.Employee;
import com.zenith.companymanagement.services.EmployeeManageService;

@UIScope
@SpringView(name= EmployeeDisplay.VIEW_NAME)
public class EmployeeDisplay extends HorizontalLayout implements View
{

	private static final long serialVersionUID = 3692395971317371136L;

	public static final String VIEW_NAME = "employeeDisplay";
	
	EditorLayout editorLayout;
	
	Grid<Employee> employeeTable;
	
	@Autowired
	EmployeeManageService employeeManageService;

	@PostConstruct
	public void init() {
//		setMargin(true);
		setSpacing(true);

		employeeTable = new Grid<>(Employee.class);
		employeeTable.setStyleName(MaterialTheme.GRID_BORDERLESS);
		employeeTable.setColumns("fname", "lname", "dob", "email");
		
		LocalDate date = LocalDate.now();
		loadTestData(date);
//		employeeTable.setItems(employeeManageService.retrieveEmployees());
//		employeeTable.setDataProvider(
//				(sortOrder, offset, limit) ->  employeeManageService.safeSubList(offset, limit).stream()
//				, () -> employeeManageService.count());
		refreshTable();
		
		employeeTable.setSizeUndefined();
		employeeTable.addSelectionListener(event -> {
			Optional<Employee> employee = event.getFirstSelectedItem();
			Notification.show(employee.get().getId().toString());
		});
		
		editorLayout = new EditorLayout(this, employeeManageService);	
		addComponent(editorLayout);
		addComponentsAndExpand(employeeTable);
	}
	

	public void loadTestData(LocalDate date) {
//		for (int i = 0; i <500000; i++) {
			employeeManageService.addEmployee(
					new Employee("John", "Doe", Designation.Admin, date, "johndoe@mail.co", "loopy", 3, 5));
			employeeManageService.addEmployee(
					new Employee("Jessie", "Jones", Designation.Employee, date, "johndoe@mail.co", "loopy", 3, 5));
			employeeManageService.addEmployee(
					new Employee("Edgar Alan", "Poe", Designation.Admin, date, "poe@poetry.ryhtm", "poopy", 3, 5));
//		}
	}

	public void refreshTable() {
		
//		employeeTable.getDataProvider().refreshAll();
		
		employeeTable.setItems(Collections.EMPTY_LIST);
		employeeTable.setItems(employeeManageService.retrieveEmployees());
	}
}
