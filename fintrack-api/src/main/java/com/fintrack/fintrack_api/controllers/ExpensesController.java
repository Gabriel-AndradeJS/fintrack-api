package com.fintrack.fintrack_api.controllers;


import com.fintrack.fintrack_api.DTOs.ExpensesDTO;
import com.fintrack.fintrack_api.entities.Expenses;
import com.fintrack.fintrack_api.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping
    public List<Expenses> getExpenses(){
        return this.expensesService.getExpenses();
    }

    @PostMapping
    public ResponseEntity<Void> createExpenses(@RequestBody ExpensesDTO expenses){
        this.expensesService.createExpenses(expenses);
         return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expenses> updateExpenses(@PathVariable Long id, @RequestBody Expenses expenses){
        Expenses response =  this.expensesService.updateExpenses(id, expenses);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenses(@PathVariable Long id) {
        this.expensesService.deleteExpenses(id);
        return ResponseEntity.noContent().build();
    }

}
