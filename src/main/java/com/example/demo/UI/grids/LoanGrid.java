package com.example.demo.UI.grids;

import com.example.demo.models.Customer;
import com.example.demo.models.Loan;
import com.example.demo.services.LoanService;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.ButtonRenderer;

public class LoanGrid extends Grid<Loan> {

    private final LoanService service;

    public LoanGrid(LoanService service) {
        super(Loan.class);
        this.service = service;
        setUp();
    }

    private Button createRemoveButton(Loan loan) {
        Button button = new Button("", clickEvent -> {
            ListDataProvider<Loan> dataProvider = (ListDataProvider<Loan>) this
                    .getDataProvider();
            service.deleteLoan(loan);
            reloadLoansByCustomers(loan.getCustomer());
        });
        button.setIcon(VaadinIcons.TRASH);
        return button;
    }

    public void setUp() {
        setSizeFull();
        removeColumn("customer");
        setColumnReorderingAllowed(true);
        this.addComponentColumn(item -> createRemoveButton(item)).setId("Delete");
    }

    public void reloadLoansByCustomers(Customer customer) {
        setItems(service.getLoansByCustomerId(customer));
        getDataProvider().refreshAll();
    }

    public Button getDeleteButton() {
        Button deleteButton = new Button("Delete");
        return deleteButton;
    }
}
