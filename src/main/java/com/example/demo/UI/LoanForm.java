package com.example.demo.UI;

import com.example.demo.LoanTypeEnum;
import com.example.demo.services.LoanService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoanForm extends AbstractCreateForm {

    private ComboBox typeOfLoanField;
    private DateField dateOfBorrowingField;
    private TextField creditAmountField;
    private TextField interestPeriodField;

    public LoanForm() {
        addComponent(getTypeOfLoanField());
        addComponent(getDateOfBorrowingField());
        addComponent(getCreditAmountField());
        addComponent(getInterestPeriodField());
        addComponent(getSaveButton());
        addComponent(getCancelButton());
    }

    public LoanTypeEnum getTypeOfLoan() {
        return (LoanTypeEnum) typeOfLoanField.getValue();
    }

    public LocalDate getDateOfBorrowing() {
        return dateOfBorrowingField.getValue();
    }

    public Long getCreditAmount() {
        try {
            return Long.parseLong(creditAmountField.getValue());
        } catch (Exception e) {
            System.out.println("Credit amount have to be a number");
            throw e;
        }
    }

    public Integer getInterestPeriod() {
        try {
            return Integer.parseInt(interestPeriodField.getValue());
        } catch (Exception e) {
            System.out.println("Credit amount have to be a number");
            throw e;
        }
    }

    public ComboBox getTypeOfLoanField() {
        if (typeOfLoanField == null) {
            List<LoanTypeEnum> types = new ArrayList<>(Arrays.asList(LoanTypeEnum.ESTATE_LOAN, LoanTypeEnum.PERSONAL_LOAN, LoanTypeEnum.DEBT_SETTLEMENT_LOAN));
            typeOfLoanField = new ComboBox<>("Select a loan type");
            typeOfLoanField.setItems(types);
            typeOfLoanField.setIcon(VaadinIcons.LINES_LIST);
        }
        return typeOfLoanField;
    }

    public DateField getDateOfBorrowingField() {
        if (dateOfBorrowingField == null) {
            dateOfBorrowingField = new DateField("Date of borrowing");
            dateOfBorrowingField.setIcon(VaadinIcons.CALENDAR_BRIEFCASE);
        }
        return dateOfBorrowingField;
    }

    public TextField getCreditAmountField() {
        if (creditAmountField == null) {
            creditAmountField = new TextField("Credit amount");
            creditAmountField.setIcon(VaadinIcons.MONEY);
        }
        return creditAmountField;
    }

    public TextField getInterestPeriodField() {
        if (interestPeriodField == null) {
            interestPeriodField = new TextField("Interest period");
            interestPeriodField.setIcon(VaadinIcons.ARROWS_LONG_H);
        }
        return interestPeriodField;
    }
}
