package com.devfortech.HelloWord.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.devfortech.HelloWord.dto.UserDTO;

public interface UserController {
	public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable);
	public ResponseEntity<UserDTO> findById(Long id);
	public ResponseEntity<UserDTO> insert(UserDTO dto);
	public ResponseEntity<UserDTO> update(Long id, UserDTO dto);
	public ResponseEntity<UserDTO> delete(Long id);
}
