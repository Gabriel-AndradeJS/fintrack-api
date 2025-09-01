package com.fintrack.fintrack_api.DTOs;

import com.fintrack.fintrack_api.entities.Expenses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExpensesDTO {

    private Long userId;
    private Double value;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ExpensesDTO(Expenses expenses) {
        this.userId = expenses.getUser().getId();
        this.value = expenses.getValue();
        this.name = expenses.getName();
        this.createdAt = expenses.getCreatedAt();
        this.updatedAt = expenses.getUpdateAt();
    }
}
