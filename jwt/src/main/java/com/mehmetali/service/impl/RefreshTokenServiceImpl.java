package com.mehmetali.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehmetali.entity.RefreshToken;
import com.mehmetali.entity.User;
import com.mehmetali.jwt.AuthResponse;
import com.mehmetali.jwt.JwtService;
import com.mehmetali.jwt.RefreshTokenRequest;
import com.mehmetali.repository.RefreshTokenRepository;
import com.mehmetali.service.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private JwtService jwtService;
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	
	
	public boolean isRefreshTokenExpired(Date expireDate) {
		return new Date().before(expireDate);
	}
	
	

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if (optional.isEmpty()) {
			System.out.println("refreshToken gecersizdir : "+request.getRefreshToken());
		}
		RefreshToken refreshToken=optional.get();
		if (!isRefreshTokenExpired(refreshToken.getExpireDate())) {
			System.out.println("refresh token expired olmustur :" +request.getRefreshToken());
		}
		String accessToken = jwtService.genereteToken(refreshToken.getUser());
		
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));
		return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
	}

}
