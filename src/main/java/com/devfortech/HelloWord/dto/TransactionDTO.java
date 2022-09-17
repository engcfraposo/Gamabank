package com.devfortech.HelloWord.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.devfortech.HelloWord.entities.Transaction;
import com.devfortech.HelloWord.enums.TypeTransaction;

import lombok.Data;
import lombok.NonNull;

@Data
public class TransactionDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private @NonNull TypeTransaction trasaction;
	private @NonNull BigDecimal amount;

	private UserDTO user;

	public TransactionDTO(Transaction entity) {
		this.setId(entity.getId());
		this.setTrasaction(entity.getTrasaction());
		this.setAmount(entity.getAmount());
	}

}
