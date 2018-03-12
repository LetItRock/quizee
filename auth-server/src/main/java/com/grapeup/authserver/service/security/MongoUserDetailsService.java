package com.grapeup.authserver.service.security;

import com.grapeup.authserver.domain.User;
import com.grapeup.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Pavlo Tymchuk
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {
	private final UserRepository repository;

	@Autowired
	public MongoUserDetailsService(final UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		User user = repository.findOne(username);

		// TODO implement ADMIN rights based on authorities from UserDetails

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}
}
