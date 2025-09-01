package com.fintrack.fintrack_api.services;

import com.fintrack.fintrack_api.DTOs.ExpensesDTO;
import com.fintrack.fintrack_api.entities.Expenses;
import com.fintrack.fintrack_api.repositories.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    public List<Expenses> getExpenses() {
        return this.expensesRepository.findAll();
    }

    public void createExpenses(Expenses expenses){
        if (expenses.getName().equals("") && expenses.getValue().isNaN()) {
            throw new IllegalArgumentException("Name and value must be filled");
        }
        expensesRepository.save(expenses);
    }

}
