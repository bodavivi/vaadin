package com.example.demo;


import com.example.demo.UI.CustomerForm;
import com.example.demo.UI.LoanForm;
import com.example.demo.UI.grids.CustomerGrid;
import com.example.demo.UI.grids.LoanGrid;
import com.example.demo.models.Customer;
import com.example.demo.models.Loan;
import com.example.demo.services.CustomerService;
import com.example.demo.services.LoanService;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringUI
@Component
@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI implements View {

    public Navigator navigator;
    public static final String LOAN_VIEW = "loans";
    public static final String LOAN_DETAIL_VIEW = "details";

    private final CustomerService customerService;
    private final LoanService loanService;
    final VerticalLayout layout = new VerticalLayout();
    private final CustomerGrid customerGrid;
    private final LoanGrid loanGrid;
    private final CustomerForm customerForm;
    private final com.example.demo.UI.LoanForm independentLoanForm;
    private final LoanForm loanFormToNewCustomer;
    private Customer actualCustomer;
    private List<Loan> savedLoans = new ArrayList<>();

    @Autowired
    public MainUI(CustomerService customerService, LoanService loanService) {
        this.customerService = customerService;
        this.loanService = loanService;
        customerGrid = new CustomerGrid(this.customerService);
        loanGrid = new LoanGrid(this.loanService);
        customerForm = new CustomerForm();
        independentLoanForm = new LoanForm();
        loanFormToNewCustomer = new LoanForm();
        setButtons();
    }

    @Override
    protected void init(VaadinRequest request) {
        layout.setMargin(true);
        setContent(layout);
        newUserPopup();
        listCustomersWithLoans();
        newLoanToACustomerPopup();
        layout.addComponent(loanGrid);
    }

    private void newUserPopup() {
        HorizontalLayout popupContent = new HorizontalLayout();
        popupContent.setSizeFull();
        popupContent.addComponent(customerForm);
        popupContent.addComponent(loanFormToNewCustomer);
        PopupView popup = new PopupView("Create", popupContent);
        customerForm.getCancelButton().addClickListener((Button.ClickListener) event -> {
            popup.setPopupVisible(false);
            savedLoans = new ArrayList<>();
        });
        layout.addComponent(popup);
    }

    private void listCustomersWithLoans() {
        customerGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        customerGrid.addItemClickListener(
                event -> {
                    actualCustomer = event.getItem();
                    loanGrid.reloadLoansByCustomers(actualCustomer);
                });
        layout.addComponent(customerGrid);
    }

    private void newLoanToACustomerPopup() {
        VerticalLayout popupContent = new VerticalLayout();
        popupContent.addComponent(independentLoanForm);
        PopupView popup = new PopupView("Create", popupContent);
        independentLoanForm.getCancelButton().addClickListener((Button.ClickListener) event -> {
            popup.setPopupVisible(false);
        });
        layout.addComponent(popup);
    }

    private void setButtons(){
        loanFormToNewCustomer.getSaveButton().addClickListener((Button.ClickListener) event -> {
            Loan newLoan = new Loan(null, loanFormToNewCustomer.getTypeOfLoan(), loanFormToNewCustomer.getCreditAmount(), loanFormToNewCustomer.getInterestPeriod(), loanFormToNewCustomer.getDateOfBorrowing());
            savedLoans.add(newLoan);
        });
        loanFormToNewCustomer.getCancelButton().setVisible(false);

        customerForm.getSaveButton().addClickListener((Button.ClickListener) event -> {
            actualCustomer = new Customer(customerForm.getFirstName(), customerForm.getLastName(), customerForm.getDateOfBirth(), customerForm.getIdentityCardNumber());
            customerService.saveCustomer(actualCustomer);
            savedLoans.stream().forEach(loan -> {
                loan.setCustomer(actualCustomer);
                loanService.saveLoan(loan);
                savedLoans = new ArrayList<>();
            });
            customerGrid.reloadCustomers();
        });

        independentLoanForm.getSaveButton().addClickListener((Button.ClickListener) event -> {
            loanService.saveLoan(new Loan(actualCustomer, independentLoanForm.getTypeOfLoan(), independentLoanForm.getCreditAmount(), independentLoanForm.getInterestPeriod(), independentLoanForm.getDateOfBorrowing()));
            loanGrid.reloadLoansByCustomers(actualCustomer);
        });
    }
}
