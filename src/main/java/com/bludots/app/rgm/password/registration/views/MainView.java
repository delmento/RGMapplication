package com.bludots.app.rgm.password.registration.views;



//import javax.swing.text.Position;


import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.services.RequestService;
import com.bludots.app.rgm.password.registration.valueobjects.RequestVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;

@Route(value = "")
@CssImport(value = "./styles/components/style.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {  

	TextField emailField;
	Button submitButton;
	Label emailLabel;

	public MainView() {
		super();

		HorizontalLayout layout = new HorizontalLayout();
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);
		//bg
//		   this.getElement().getStyle().set( "background-color" , "bg") ;
		//getElement().getStyle().set("background-image","url('https://www.google.com/search?q=rosebel+gold+mines&rlz=1C1GCEA_enSR944SR944&sxsrf=AOaemvKiUtoGt_FWP3vqtSZVz5OMf8VsxQ:1632222978886&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjCzOa2-I_zAhWuTDABHRMUA38Q_AUoAXoECAEQAw&biw=1280&bih=609&dpr=1.5#imgrc=Z5nNSb9arYu5AM')");

		// <img class="logo-img" src="SENCOR_Logo.jpg" ALT="align box" ALIGN=CENTER>

		// email
		EmailField emailField = new EmailField("Email");
		emailField.setPlaceholder("Enter your email");
		emailField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
		emailField.setWidth("300px");

		Button sendbutton = new Button("Send Email");
		//sendbutton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		// button.addClickListener(this::showButtonClickedMessage);

		add(emailField, sendbutton);
		sendbutton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				//check email validation
				RequestVO requestvo = new RequestVO();
				requestvo.setEmail(emailField.getValue());
				RequestService requestService = ContextProvider.getBean(RequestService.class);
				requestService.persistRequest(requestvo);

				Notification.show(
						"Please check your inbox for instructions.",
						4000, Position.TOP_CENTER);
				emailField.clear();
			}

		});

	}
}
