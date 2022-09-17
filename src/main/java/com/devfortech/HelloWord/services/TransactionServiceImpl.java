package com.devfortech.HelloWord.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.TransactionDTO;
import com.devfortech.HelloWord.entities.Transaction;
import com.devfortech.HelloWord.enums.TypeTransaction;
import com.devfortech.HelloWord.repositories.TransactionRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<TransactionDTO> findAll(Pageable pageable) {
		Page<Transaction> page = repository.findAll(pageable);
		return page.map( entity -> new TransactionDTO(entity));
	}

	@Override
	@Transactional(readOnly = true)
	public TransactionDTO findById(Long id) {
		Optional<Transaction> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new ResourceNotFoundException("Client not found "+id);
		}
		Transaction entity = obj.get();
		return new TransactionDTO(entity);
	}

	@Override
	@Transactional()
	public TransactionDTO insert(TransactionDTO dto) {
		if(dto.getTrasaction() == TypeTransaction.CREDIT ) {
			throw new ResourceNotFoundException("Transaction is invalid");
		}
		if(dto.getTrasaction() == TypeTransaction.DEBIT ) {
			throw new ResourceNotFoundException("Transaction is invalid");
		}
		Transaction entity = new Transaction();
		copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return new TransactionDTO(entity);
	}

	@Override
	@Transactional()
	public TransactionDTO update(Long id, TransactionDTO dto) {
		Optional<Transaction> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new ResourceNotFoundException("Client not found "+id);
		}
		Transaction entity = obj.get();
		copyDTOToEntity(dto, entity);
		entity.setId(id);
		entity = repository.save(entity);
		return new TransactionDTO(entity);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Client not found!" + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Ïntegrity violation");
		}
	}
	
	private void copyDTOToEntity(TransactionDTO dto, Transaction entity) {
		entity.setId(dto.getId());
		entity.setTrasaction(dto.getTrasaction());
		entity.setAmount(dto.getAmount());
	}

}