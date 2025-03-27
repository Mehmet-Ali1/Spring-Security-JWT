package com.mehmetali.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mehmetali.entity.RefreshToken;
import java.util.List;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	@Query(value = "from RefreshToken r where r.refreshToken = :refreshToken")
	public Optional<RefreshToken> findByRefreshToken(String refreshToken);
	
}
