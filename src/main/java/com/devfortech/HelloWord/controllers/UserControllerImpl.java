package com.devfortech.HelloWord.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devfortech.HelloWord.dto.UserDTO;
import com.devfortech.HelloWord.services.UserService;

public class UserControllerImpl implements UserController {

	@Autowired
	private UserService service;
	
	@Override
	public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
		Page<UserDTO> page = service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}

	@Override
	public ResponseEntity<UserDTO> findById(Long id) {
		UserDTO body = service.findById(id);
		return ResponseEntity.ok().body(body);
	}

	@Override
	public ResponseEntity<UserDTO> insert(UserDTO dto) {
		UserDTO body = service.insert(dto);
		return ResponseEntity.ok().body(body);
	}

	@Override
	public ResponseEntity<UserDTO> update(Long id, UserDTO dto) {
		UserDTO body = service.update(id,dto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(body);
	}

	@Override
	public ResponseEntity<UserDTO> delete(Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
