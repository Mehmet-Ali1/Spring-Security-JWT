package com.mehmetali.controller;

import com.mehmetali.dto.DtoUser;
import com.mehmetali.jwt.AuthRequest;
import com.mehmetali.jwt.AuthResponse;
import com.mehmetali.jwt.RefreshTokenRequest;

public interface IRestAuthController {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
}
