package com.mehmetali.service;

import com.mehmetali.dto.DtoUser;
import com.mehmetali.jwt.AuthRequest;
import com.mehmetali.jwt.AuthResponse;

public interface IAuthService {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
}
