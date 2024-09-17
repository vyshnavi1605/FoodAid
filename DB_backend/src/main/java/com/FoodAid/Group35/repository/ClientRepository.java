package com.FoodAid.Group35.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.FoodAid.Group35.entity.Client;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByUsername(String userName);
    Client findById(long clientID);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("select u from Client u where u.username = :username")
    public Client getClientByUsername(@Param("username") String username);

	
    
    
    
}

