package br.com.freitas.msuser.services;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.freitas.msuser.domain.User;
import br.com.freitas.msuser.producers.UserProducer;
import br.com.freitas.msuser.repo.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	final UserRepository userRepository;
	final UserProducer userProducer;

	public UserService(UserRepository userRepository, UserProducer userProducer) {
		this.userRepository = userRepository;
		this.userProducer = userProducer;
	}

	@Transactional
	public User save(User model) {
		model = userRepository.save(model);
		userProducer.publishMessage(model);
		return model;
	}
	
	public Page<User> findAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

}
