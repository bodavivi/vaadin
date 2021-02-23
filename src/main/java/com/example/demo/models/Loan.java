package com.example.demo.models;

import com.example.demo.LoanTypeEnum;
import com.vaadin.ui.Button;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private LoanTypeEnum typeOfLoan;
    private long creditAmount;
    private int interestPeriod;
    private LocalDate dateOfBorrowing;

    public Loan() {
    }

    public Loan(Customer customer, LoanTypeEnum typeOfLoan, long creditAmount, int interestPeriod, LocalDate dateOfBorrowing) {
        this.customer = customer;
        this.typeOfLoan = typeOfLoan;
        this.creditAmount = creditAmount;
        this.interestPeriod = interestPeriod;
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanTypeEnum getTypeOfLoan() {
        return typeOfLoan;
    }

    public void setType(LoanTypeEnum typeOfLoan) {
        this.typeOfLoan = typeOfLoan;
    }

    public long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getInterestPeriod() {
        return interestPeriod;
    }

    public void setInterestPeriod(int interestPeriod) {
        this.interestPeriod = interestPeriod;
    }

    public LocalDate getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public void setDateOfBorrowing(LocalDate dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }
}
