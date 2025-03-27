package com.mehmetali.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mehmetali.dto.DtoUser;
import com.mehmetali.entity.RefreshToken;
import com.mehmetali.entity.User;
import com.mehmetali.jwt.AuthRequest;
import com.mehmetali.jwt.AuthResponse;
import com.mehmetali.jwt.JwtService;
import com.mehmetali.repository.RefreshTokenRepository;
import com.mehmetali.repository.UserRepository;
import com.mehmetali.service.IAuthService;

@Service
public class AuthService implements IAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setUser(user);
		return refreshToken;
	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUserName(),
					request.getPassword());
			authenticationProvider.authenticate(auth);
			Optional<User> optinalUser = userRepository.findByUsername(request.getUserName());
			String accessToken = jwtService.genereteToken(optinalUser.get());
			
			RefreshToken refreshToken= createRefreshToken(optinalUser.get());
			refreshTokenRepository.save(refreshToken);
			
			return new AuthResponse(accessToken, refreshToken.getRefreshToken());
		} catch (Exception e) {
			System.out.println("kulanici adi veya sifre hatali");
		}
		return null;
	}

	@Override
	public DtoUser register(AuthRequest request) {
		DtoUser dtoUser = new DtoUser();
		User user = new User();
		user.setUserName(request.getUserName());
		user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, dtoUser);

		return dtoUser;
	}

}
