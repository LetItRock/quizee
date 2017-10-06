package com.grapeup.authserver.service;

import com.grapeup.authserver.domain.User;
import com.grapeup.authserver.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Pavlo Tymchuk
 */
@Service
public class UserServiceImpl implements UserService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void create(final User user) {
		User existingUser = userRepository.findOne(user.getUsername());
		Assert.isNull(existingUser, "User already exists: " + user.getUsername());

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);

		userRepository.save(user);

		LOGGER.info("New user has been created: {}", user.getUsername());
	}
}
