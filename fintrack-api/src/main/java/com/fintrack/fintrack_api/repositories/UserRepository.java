package com.fintrack.fintrack_api.repositories;

import com.fintrack.fintrack_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
