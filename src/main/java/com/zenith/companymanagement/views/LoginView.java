package com.zenith.companymanagement.views;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.zenith.companymanagement.domain.Designation;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View
{

	private static final long serialVersionUID = 8398816963887429648L;

	public static final String VIEW_NAME = "login";
	 
	TextField username;
	PasswordField password;
	Button loginButton;
	Label loginLabel;

	ComboBox<Designation> designation;

	@PostConstruct
	public void init () {
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		loginLabel = new Label("Login");
		loginLabel.addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_LIGHT);

		username = new TextField("Username");
		password = new PasswordField("Password");

		designation = new ComboBox<>("Select Position");
		designation.setTextInputAllowed(false);
		designation.setEmptySelectionAllowed(false);
		designation.setValue(Designation.Admin);
		designation.setItems(Designation.values());

		designation.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);

		loginButton = new Button("Login");
		loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		loginButton.addClickListener(click -> {
			getUI().getNavigator().navigateTo(EmployeeDisplay.VIEW_NAME);
		});

		addComponents(loginLabel, username, password, designation, loginButton);
	}


	@Override
	public void enter(ViewChangeEvent event) {
	}
}
