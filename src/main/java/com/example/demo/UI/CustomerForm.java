package com.example.demo.UI;

import com.example.demo.UI.grids.CustomerGrid;
import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import java.time.LocalDate;

public class CustomerForm extends AbstractCreateForm {

    private TextField firstNameField;
    private TextField lastNameField;
    private DateField dateOfBirthField;
    private TextField identityCardNumber;

    public CustomerForm() {
        addComponent(getFirstNameField());
        addComponent(getLastNameField());
        addComponent(getDateOfBirthField());
        addComponent(getIdentityCardNumberField());
        addComponent(getSaveButton());
        addComponent(getCancelButton());
    }

    public TextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new TextField("First name");
            firstNameField.setIcon(VaadinIcons.CLIPBOARD_USER);
        }
        return firstNameField;
    }

    public TextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new TextField("Last name");
            lastNameField.setIcon(VaadinIcons.FAMILY);
        }
        return lastNameField;
    }

    public DateField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new DateField("Date of birth");
            dateOfBirthField.setIcon(VaadinIcons.MORNING);
        }
        return dateOfBirthField;
    }

    public TextField getIdentityCardNumberField() {
        if (identityCardNumber == null) {
            identityCardNumber = new TextField("Identity card number");
            identityCardNumber.setIcon(VaadinIcons.USER_CARD);
        }
        return identityCardNumber;
    }

    public String getFirstName(){
        return firstNameField.getValue();
    }

    public String getLastName(){
        return lastNameField.getValue();
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirthField.getValue();
    }

    public String getIdentityCardNumber(){
        return identityCardNumber.getValue();
    }

   public void setSaveButton(CustomerService customerService, CustomerGrid customerGrid){
       getSaveButton().addClickListener((Button.ClickListener) event -> {
           customerService.saveCustomer(new Customer(getFirstName(), getLastName(), getDateOfBirth(), getIdentityCardNumber()));
           customerGrid.reloadCustomers();
       });
   }
}
