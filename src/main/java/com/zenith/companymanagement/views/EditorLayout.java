package com.zenith.companymanagement.views;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import com.zenith.companymanagement.domain.Designation;
import com.zenith.companymanagement.domain.Employee;
import com.zenith.companymanagement.services.EmployeeManageService;

@UIScope
public class EditorLayout extends FormLayout
{

	private static final long serialVersionUID = 6157994134490605049L;
	private TextField fname;
    private TextField lname;
    private DateField dob;
    private TextField email;
    private PasswordField password;
    
    private  Button backButton;
    private Button saveButton;

    private ComboBox<Designation> position;
    
    private Binder<Employee> binder;
    
	private Employee newEmployee;
	
	private EmployeeDisplay display;
	
	public EmployeeManageService employeeManageService;

    public EditorLayout(EmployeeDisplay employeeDisplay, EmployeeManageService employeeManager) {
    
    	display = employeeDisplay;
    	employeeManageService = employeeManager;
    	
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        setSizeUndefined();

        fname = new TextField("First Name");
        fname.focus();
        
        lname = new TextField("Last Name");
        
        email = new TextField("Email");
        password = new PasswordField("Password");
        
        dob = new DateField("Date of Birth");
        
        position = new ComboBox<>("Position");
        position.setItems(Designation.values());
        position.setValue(Designation.Admin);
        
        binder = new Binder<Employee>(Employee.class);
        newEmployee = new Employee();
        binder.bindInstanceFields(this);
        binder.setBean(newEmployee);
        
        backButton = new Button(VaadinIcons.CLOSE);
        backButton.addStyleName(ValoTheme.BUTTON_DANGER);
        backButton.addClickListener(click -> {
            getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
        });
        
        saveButton = new Button(VaadinIcons.EDIT);
        saveButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        saveButton.addClickListener((event)-> {
        	try {
				binder.writeBean(newEmployee);
        		employeeManageService.addEmployee(newEmployee);
        		display.refreshTable();
				Notification.show(newEmployee.getFname()+ " entry added successfully!");
			} catch (Exception e) {
				Notification.show("Failed to add "+ newEmployee.getFname());
				e.printStackTrace();
			}
        });
        
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponentsAndExpand(saveButton, backButton);

        addComponents(fname, lname, dob, email, password, position, buttonLayout);
    }
}
