package com.devfortech.HelloWord.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.UserDTO;
import com.devfortech.HelloWord.entities.User;
import com.devfortech.HelloWord.repositories.UserRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> page = repository.findAll(pageable);
		return page.map( entity -> new UserDTO(entity));
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new ResourceNotFoundException("Client not found "+id);
		}
		User entity = obj.get();
		return new UserDTO(entity);
	}

	@Override
	@Transactional()
	public UserDTO insert(UserDTO dto) {
		Optional<User> hasCpf = repository.findByCpf(dto.getCpf());
		if(!hasCpf.isPresent()) {
			throw new ResourceNotFoundException("Client already have a CPF ");
		}
		User entity = new User();
		copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Override
	@Transactional()
	public UserDTO update(Long id, UserDTO dto) {
		Optional<User> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new ResourceNotFoundException("Client not found "+id);
		}
		User entity = obj.get();
		copyDTOToEntity(dto, entity);
		entity.setId(id);
		entity = repository.save(entity);
		return new UserDTO(entity);
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
	
	private void copyDTOToEntity(UserDTO dto, User entity) {
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
		entity.setPassword(dto.getPassword());
	}

}
