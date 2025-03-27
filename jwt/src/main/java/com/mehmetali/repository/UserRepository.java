package com.mehmetali.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mehmetali.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
@Query(value = "from User where userName = :username")
	Optional<User> findByUsername(String username);
	
}
