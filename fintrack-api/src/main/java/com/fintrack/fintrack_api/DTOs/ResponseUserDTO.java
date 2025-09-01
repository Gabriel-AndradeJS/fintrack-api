package com.fintrack.fintrack_api.DTOs;

import com.fintrack.fintrack_api.entities.Expenses;
import com.fintrack.fintrack_api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {
    private Long id;
    private String name;
    private String email;
    private Double totalBalance;
    private Double currentBalance;
    private List<Expenses> expenses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResponseUserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.totalBalance = user.getBalance();
        this.currentBalance = user.getCurrentBalance();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdateAt();
        this.expenses = user.getExpenses();
    }
}
