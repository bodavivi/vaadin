package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Loan;
import com.example.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private LoanRepository repository;

    @Autowired
    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public List<Loan> getLoansByCustomerId(Customer customer){
        return repository.findAllByCustomer(customer);
    }

    public Loan saveLoan(Loan loan){
        return repository.save(loan);
    }

    public List<Loan> getLoans() {
        return repository.findAll();
    }

    public void deleteLoan(Loan loan){
        repository.deleteById(loan.getId());
    }
}
