package com.fintrack.fintrack_api.repositories;

import com.fintrack.fintrack_api.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
