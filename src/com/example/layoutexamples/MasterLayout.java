package com.example.layoutexamples;

import com.vaadin.ui.VerticalLayout;

public class MasterLayout extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3369826877129680159L;

	public MasterLayout() {
		// TODO Auto-generated constructor stub
		super();
		addComponent(new UserForm());
		
		UserPanel userPanel=new UserPanel("User Panel");
		userPanel.setSizeUndefined(); // Shrink to fit content
		addComponent(userPanel);
		
		userPanel.setContent(new UserForm());
	}

}
