package com.devfortech.HelloWord.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfortech.HelloWord.entities.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {}
