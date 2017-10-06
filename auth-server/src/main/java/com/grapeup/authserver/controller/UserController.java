package com.grapeup.authserver.controller;

import com.grapeup.authserver.domain.User;
import com.grapeup.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Pavlo Tymchuk
 */
@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/current")
	public Principal getLoggedUser(Principal principal) {
		return principal;
	}

	// @PreAuthorize("#oauth2.hasScope('server')")
	@PostMapping
	public void createUser(@Valid @RequestBody User user) {
		userService.create(user);
	}

}
