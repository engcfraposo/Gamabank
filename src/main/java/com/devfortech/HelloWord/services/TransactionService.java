package com.devfortech.HelloWord.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devfortech.HelloWord.dto.TransactionDTO;

public interface TransactionService {
	public Page<TransactionDTO> findAll(Pageable pageable);
	public TransactionDTO findById(Long id);
	public TransactionDTO insert(TransactionDTO dto);
	public TransactionDTO update(Long id, TransactionDTO dto);
	public void delete(Long id);
}
