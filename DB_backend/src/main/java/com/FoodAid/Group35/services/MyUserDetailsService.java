package com.FoodAid.Group35.services;

import com.FoodAid.Group35.entity.Client;
import com.FoodAid.Group35.model.MyUserDetails;
import com.FoodAid.Group35.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<Client> client = clientRepository.findByUsername(userName);

       client.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + userName));
       return client.map(MyUserDetails::new).get();
    }
}
