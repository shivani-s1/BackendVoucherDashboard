package com.voucher.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.voucher.entity.User;
import com.voucher.exceptions.UserAlreadyExistException;
import com.voucher.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	 private final UserRepository repo;
	    private final SequenceGeneratorService sequenceGenerator;
	    private final PasswordEncoder passwordEncoder;

	    @Autowired
	    public UserServiceImpl(UserRepository repo, SequenceGeneratorService sequenceGenerator, PasswordEncoder passwordEncoder) {
	        this.repo = repo;
	        this.sequenceGenerator = sequenceGenerator;
	        this.passwordEncoder = passwordEncoder;
	    }
	
	@Override
	public Optional<User> register(User user) throws UserAlreadyExistException {
		
		Optional<User> u = repo.findByUserEmail(user.getUserEmail());
		
		if(u.isPresent())
		{
			throw new UserAlreadyExistException();
		}
		user.setUserId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User us = repo.insert(user);
		
		return Optional.of(us);
	}

	


}
