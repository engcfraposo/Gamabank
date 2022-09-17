package com.devfortech.HelloWord.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.devfortech.HelloWord.entities.User;

import lombok.Data;
import lombok.NonNull;


@Data
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private @NonNull String email;
	private @NonNull String cpf;
	private @NonNull String password;
	private List<TransactionDTO> transactions = new ArrayList<>();
	
	public UserDTO(User entity) {
		this.setId(entity.getId());
		this.setEmail(entity.getEmail());
		this.setCpf(entity.getCpf());
		this.setPassword(entity.getPassword());
	}
}
