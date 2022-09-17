package com.devfortech.HelloWord.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfortech.HelloWord.entities.User;

public interface UserRepository  extends JpaRepository<User, Long> {
	Optional<User> findByCpf(String cpf);
}
