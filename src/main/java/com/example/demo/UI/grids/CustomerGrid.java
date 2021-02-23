package com.example.demo.UI.grids;

import com.example.demo.models.Customer;
import com.example.demo.models.Loan;
import com.example.demo.services.CustomerService;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;

public class CustomerGrid extends Grid<Customer> {

    private final CustomerService service;
    private Button createButton;

    public CustomerGrid(CustomerService service) {
        super(Customer.class);
        this.service = service;
        setUp();
    }

    public void setUp() {
        setCustomers();
        setSizeFull();
        removeColumn("loans");
        setColumnOrder("id", "firstName", "lastName", "dateOfBirth", "identityCardNumber");
        setColumnReorderingAllowed(true);
        this.addComponentColumn(item -> createRemoveButton(item)).setId("Delete");
    }

    public void setCustomers() {
        setItems(service.getCustomers());
    }

    public void reloadCustomers() {
        setCustomers();
        getDataProvider().refreshAll();
    }

    private Button createRemoveButton(Customer customer) {
        Button button = new Button("", clickEvent -> {
            ListDataProvider<Customer> dataProvider = (ListDataProvider<Customer>) this
                    .getDataProvider();
            service.deleteCustomer(customer);
            reloadCustomers();
        });
        button.setIcon(VaadinIcons.TRASH);
        return button;
    }
}
