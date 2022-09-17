package com.devfortech.HelloWord.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devfortech.HelloWord.dto.UserDTO;

public interface UserService {
	public Page<UserDTO> findAll(Pageable pageable);
	public UserDTO findById(Long id);
	public UserDTO insert(UserDTO dto);
	public UserDTO update(Long id, UserDTO dto);
	public void delete(Long id);
}
