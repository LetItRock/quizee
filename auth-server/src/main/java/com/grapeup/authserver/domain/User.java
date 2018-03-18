package com.grapeup.authserver.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pavlo Tymchuk
 */
@Document(collection = "users")
public class User implements UserDetails {
	@Id
	private String username;
	private String password;
	private boolean isEnabled = true;
	private List<String> roles = new ArrayList<>();

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void addRole(String role) {
		Assert.notNull(role, "Role cannot be null");
		this.roles.add(role);
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return roles
				.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(final boolean enabled) {
		isEnabled = enabled;
	}
}
