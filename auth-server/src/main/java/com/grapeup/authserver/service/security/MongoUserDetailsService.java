package com.grapeup.authserver.service.security;

import com.grapeup.authserver.domain.Roles;
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
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		if (username.equals("admin@admin.com")) {
			user.addRole(Roles.ROLE_ADMIN.name());
		} else {
			user.addRole(Roles.ROLE_USER.name());
		}

		return user;
	}
}
