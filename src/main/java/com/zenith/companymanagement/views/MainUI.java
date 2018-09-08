package com.zenith.companymanagement.views;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay
{
	private static final long serialVersionUID = -4485483471385365911L;
	
	Panel window;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout root = new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		root.setStyleName(MaterialTheme.MENU_ROOT);
		root.setSizeFull();
		window = new Panel();
		window.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
		window.setSizeFull();
		root.addComponentsAndExpand(window);
		setContent(root);
		getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
	}

	@Override
	public void showView(View view) {
		window.setContent((Component)view);
	}
	
}
