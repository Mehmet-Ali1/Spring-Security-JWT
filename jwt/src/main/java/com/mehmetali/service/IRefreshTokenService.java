package com.mehmetali.service;

import com.mehmetali.jwt.AuthResponse;
import com.mehmetali.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {

	public AuthResponse refreshToken(RefreshTokenRequest request);
}
