package com.example.layoutexamples;

import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class UserForm extends FormLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7424300334120381088L;

	public UserForm(){
		super();
		
		TextField tf1 = new TextField("Name");
		tf1.setIcon(FontAwesome.USER);
		tf1.setRequired(true);
		tf1.addValidator(new NullValidator("Must be given", false));
		addComponent(tf1);
		
		TextField tf2 = new TextField("Street address");
		tf2.setIcon(FontAwesome.ROAD);
		addComponent(tf2);
		
		TextField tf3 = new TextField("Postal code");
		tf3.setIcon(FontAwesome.ENVELOPE);
		tf3.addValidator(new IntegerRangeValidator("Doh!", 1, 99999));
		addComponent(tf3);
		
	}

}
