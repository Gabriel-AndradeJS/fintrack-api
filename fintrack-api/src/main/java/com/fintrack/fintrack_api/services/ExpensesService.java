package com.fintrack.fintrack_api.services;

import com.fintrack.fintrack_api.DTOs.ExpensesDTO;
import com.fintrack.fintrack_api.entities.Expenses;
import com.fintrack.fintrack_api.entities.User;
import com.fintrack.fintrack_api.repositories.ExpensesRepository;
import com.fintrack.fintrack_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Expenses> getExpenses() {
        return this.expensesRepository.findAll();
    }

    public void createExpenses(ExpensesDTO expensesDTO){

        if (expensesDTO.getName().equals("") && expensesDTO.getValue().isNaN()) {
            throw new IllegalArgumentException("Name and value must be filled");
        }

        Optional<User> user = this.userRepository.findById(expensesDTO.getUserId());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Expenses expensesSave = new Expenses();
        expensesSave.setName(expensesDTO.getName());
        expensesSave.setValue(expensesDTO.getValue());
        expensesSave.setUser(user.get());
        expensesRepository.save(expensesSave);
    }

    public Expenses updateExpenses(Long id, Expenses expensesDTO) {
        Expenses expenses = this.expensesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Expenses not found")
        );


        if (expensesDTO.getName() != null) {
            expenses.setName(expensesDTO.getName());
        }

        if (expensesDTO.getValue() != null) {
            expenses.setValue(expensesDTO.getValue());
        }

        if (expensesDTO.getUser() != null) {
            expenses.setUser(expensesDTO.getUser());
        }

        return this.expensesRepository.save(expenses);
    }

    public ResponseEntity<Void> deleteExpenses(Long id) {
        this.expensesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Expenses not found")
        );
        this.expensesRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
