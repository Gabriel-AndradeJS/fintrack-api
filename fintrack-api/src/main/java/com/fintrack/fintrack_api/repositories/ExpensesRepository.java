package com.fintrack.fintrack_api.repositories;

import com.fintrack.fintrack_api.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
}
