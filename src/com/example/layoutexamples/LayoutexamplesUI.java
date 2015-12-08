package com.example.layoutexamples;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("layoutexamples")
public class LayoutexamplesUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7989756359244960317L;
	MasterLayout masterLayout;
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = LayoutexamplesUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		masterLayout = new MasterLayout(); 
		setContent(masterLayout);
		
		
		Button button = new Button("Show Sub-Window");
		Button button1 = new Button("Show Modal Window");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				openSubWindow();
				masterLayout.addComponent(new Label("Sub Window has opened"));
			}
		});
		
		button1.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				openSubWindowModal();
				masterLayout.addComponent(new Label("Modal Window has opened"));
			}
		});
		masterLayout.addComponent(button);
		masterLayout.addComponent(button1);
		
		TabSheet myTab=createTabPanel();
		
		myTab.addSelectedTabChangeListener(new SelectedTabChangeListener() {
			
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				// TODO Auto-generated method stub
				
				// Find the tabsheet
				TabSheet tabsheet = event.getTabSheet();
				// Find the tab (here we know it's a layout)
				Layout tab = (Layout) tabsheet.getSelectedTab();
				// Get the tab caption from the tab object
				String caption = tabsheet.getTab(tab).getCaption();
				
				masterLayout.addComponent(new Label("Selected Tab : " + caption));
				
			}
		});
		
		Panel myPanel=new Panel();
		myPanel.setContent(myTab);
		myPanel.setSizeUndefined();
		
		masterLayout.addComponent(myPanel);
		
	}
	
	public void openSubWindow(){
		
		// Create a sub-window and set the content
		Window subWindow = new Window("Sub-window");
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
		subWindow.setContent(new UserForm());
		// Put some components in it
		subContent.addComponent(new Label("Meatball sub"));
		subContent.addComponent(new Button("Awlright"));
		// Center it in the browser window
		subWindow.center();
		// Open it in the UI
		subWindow.setSizeUndefined();
		addWindow(subWindow);
	}
	
public void openSubWindowModal(){
		
		// Create a sub-window and set the content
		Window subWindow = new Window("Modal-window");
		subWindow.setContent(new UserForm());
		subWindow.center();
		// Open it in the UI
		subWindow.setSizeUndefined();
		subWindow.setResizable(false);
		subWindow.setModal(true);
		addWindow(subWindow);
	}

	public TabSheet createTabPanel(){
		
		TabSheet tabsheet = new TabSheet();
		// Create the first tab
		VerticalLayout tab1 = new VerticalLayout();
		tab1.addComponent(new UserForm());
		tabsheet.addTab(tab1, "Mercury",FontAwesome.ROAD);
		// This tab gets its caption from the component caption
		VerticalLayout tab2 = new VerticalLayout();
		tab2.addComponent(new UserForm());
		tab2.setCaption("Venus");
		tabsheet.addTab(tab2).setIcon(FontAwesome.SITEMAP);
		
		return tabsheet;
	}



}