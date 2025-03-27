package com.mehmetali.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mehmetali.controller.IRestAuthController;
import com.mehmetali.dto.DtoUser;
import com.mehmetali.jwt.AuthRequest;
import com.mehmetali.jwt.AuthResponse;
import com.mehmetali.jwt.RefreshTokenRequest;
import com.mehmetali.service.IAuthService;
import com.mehmetali.service.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController

public class RestAuthControllerImpl implements IRestAuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private IRefreshTokenService refreshTokenService;

	@PostMapping(path = "/register")
	@Override
	public DtoUser register(@Valid @RequestBody AuthRequest request) {
		return authService.register(request);
	}

	@PostMapping(path = "/authenticate")
	@Override
	public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
		return authService.authenticate(request);
	}

	@PostMapping(path = "/refreshToken")
	@Override
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {

		return refreshTokenService.refreshToken(request);
	}

}
