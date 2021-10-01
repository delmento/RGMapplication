package com.bludots.app.rgm.password.registration.views;

import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.services.CredentialService;
import com.bludots.app.rgm.password.registration.valueobjects.CredentialVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;

@Route(value = "pwd")
public class PasswordFormView extends VerticalLayout {
	TextField emailField;
	Button submitButton;
	Label emailLabel;
	TextField passwordField;

	public PasswordFormView() {
		super();
		//
		
		
		HorizontalLayout layout = new HorizontalLayout();
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		//emailfield
		//check of @gmail ingevoerd is
		TextField email = new TextField("Email");
		email.setPlaceholder("Enter your email");
		email.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
		email.setErrorMessage("invalid");
		email.setPattern("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$");
		email.setWidth("300px");
		
		//passwordfield
		PasswordField password = new PasswordField();
		password.setLabel("Password");
		password.setPlaceholder("Enter password");
		password.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
		password.setWidth("300px");
		
		//confirmpasswordfield
		PasswordField reenterpassword = new PasswordField();
		reenterpassword.setLabel("Re-Enter Password");
		reenterpassword.setPlaceholder("Enter password");
		reenterpassword.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
		reenterpassword.setWidth("300px");
		
		
		Button submitbutton = new Button("Submit");
		submitbutton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        // button.addClickListener(this::showButtonClickedMessage);
		add(email, password, reenterpassword, submitbutton);
		
		submitbutton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				CredentialVO vo = new CredentialVO();
				vo.setEmail(email.getValue());
				vo.setPassword(password.getValue());
				vo.setConfirmPassword(reenterpassword.getValue());
			
				//check of je password correct is
				if (vo.getPassword() != vo.getConfirmPassword()){
				    Notification.show("Entered passwords does not match", 4000, Position.TOP_CENTER);
				    }
				else {
				
				CredentialService changePasswordService = ContextProvider
						.getBean(CredentialService.class);
				changePasswordService.persistPasswordChange(vo);
				Notification.show("You password is correct .",
						4000, Position.TOP_CENTER);
				email.clear();
				password.clear();
				reenterpassword.clear();
				}
			}
			
			
		});

		
	}

}

